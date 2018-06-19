/**
 * This software was developed and / or modified by Raytheon Company,
 * pursuant to Contract DG133W-05-CQ-1067 with the US Government.
 *
 * U.S. EXPORT CONTROLLED TECHNICAL DATA
 * This software product contains export-restricted data whose
 * export/transfer/disclosure is restricted by U.S. law. Dissemination
 * to non-U.S. persons whether in the United States or abroad requires
 * an export license or other authorization.
 *
 * Contractor Name:        Raytheon Company
 * Contractor Address:     6825 Pine Street, Suite 340
 *                         Mail Stop B8
 *                         Omaha, NE 68106
 *                         402.291.0100
 *
 * See the AWIPS II Master Rights File ("Master Rights File.pdf") for
 * further licensing information.
 **/
package com.raytheon.edex.plugin.gfe.reference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.geotools.data.DataStore;
import org.geotools.data.Query;
import org.geotools.data.postgis.PostgisNGDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.geometry.BoundingBox;

import com.raytheon.edex.plugin.gfe.exception.GfeConfigurationException;
import com.raytheon.edex.plugin.gfe.exception.MissingLocalMapsException;
import com.raytheon.uf.edex.core.EDEXUtil;
import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

/**
 * Class to allow reading (and optionally filtering) of an PostGIS table
 *
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date          Ticket#  Engineer  Description
 * ------------- -------- --------- --------------------------------------------
 * Sep 18, 2012  1091     randerso  Initial creation
 * Mar 28, 2013  1837     dgilling  Change error handling in getLastUpdated().
 * Mar 11, 2014  2718     randerso  Changes for GeoTools 10.5
 * Oct 16, 2014  3454     bphillip  Upgrading to Hibernate 4
 * Jul 13, 2015  4500     rjpeter   Fix SQL Injection concerns.
 * Oct 06, 2016  5526     randerso  Fix thread safety problem to ensure
 *                                  datastore is properly disposed. Code and
 *                                  JavaDoc cleanup.
 * Oct 17, 2016  5526     randerso  Fix additional thread safety issue when
 *                                  dataStore is created.
 * Nov 21, 2016  19512    dfriedman Support SSL PostgreSQL connections.
 *
 * </pre>
 *
 * @author randerso
 * @version 1.0
 */

public class DbShapeSource {
    /**
     * Enum for type of geometry
     */
    public static enum ShapeType {
        /** Unknown geometry type */
        NONE,

        /** Point/multipoint geometry */
        POINT,

        /** Line/multiline geometry */
        POLYLINE,

        /** Polygon/multipolygon geometry */
        POLYGON
    }

    private static final String DB_NAME = "maps";

    private static final String SCHEMA_NAME = "mapdata";

    private static Object dataStoreLock = new Object();

    private static DataStore _dataStore;

    private static AtomicInteger refCount = new AtomicInteger(0);

    private boolean filtered = false;

    private String displayName;

    private boolean hasEditAreaName = false;

    private String groupName;

    private String instanceName;

    private final String tableName;

    private List<String> attributeNames;

    private SimpleFeatureCollection featureCollection;

    private SimpleFeatureIterator featureIterator;

    private String shapeField;

    private ShapeType type;

    private Geometry boundingGeom;

    private BoundingBox boundingBox;

    private Query query;

    private SimpleFeatureType schema;

    /**
     * Create a DbShapeSource
     *
     * @param tableName
     *            the name of the database table.
     * @throws IOException
     */
    public DbShapeSource(String tableName) throws IOException {
        this.tableName = tableName.toLowerCase();
        refCount.incrementAndGet();
    }

    @Override
    protected void finalize() throws Throwable {
        /*
         * We only have one static instance of the dataStore since it is
         * expensive to keep creating and disposing the dataStore
         */
        synchronized (dataStoreLock) {
            if (refCount.decrementAndGet() == 0) {
                if (_dataStore != null) {
                    _dataStore.dispose();
                    _dataStore = null;
                }
            }
        }
        super.finalize();
    }

    private class SSLEnabledPostgisNGDataStoreFactory extends PostgisNGDataStoreFactory {
        @Override
        protected String getJDBCUrl(Map params) throws IOException {
            StringBuilder sb = new StringBuilder(super.getJDBCUrl(params));
            String[] sslParams = { "sslmode", "sslcert", "sslkey", "sslrootcert" };
            boolean first = sb.indexOf("?") < 0;
            for (String param : sslParams) {
                Object value = params.get(param);
                if (value instanceof String) {
                    sb.append(first ? '?' : '&').append(param).append('=').append(value);
                    first = false;
                }
            }
            return sb.toString();
        }
    }

    private DataStore getDataStore() throws IOException {
        synchronized (dataStoreLock) {
            if (_dataStore == null) {
                SessionFactoryImplementor sessionFactory = (SessionFactoryImplementor) EDEXUtil
                        .getESBComponent("mapsSessionFactory");
                Properties props = sessionFactory.getProperties();
                String host = props.getProperty("db.addr");
                String port = props.getProperty("db.port");
                String user = props.getProperty("connection.username");
                String passwd = props.getProperty("connection.password");
                String sslMode = props.getProperty("connection.sslmode");
                String cert = props.getProperty("connection.sslcert");
                String key = props.getProperty("connection.sslkey");
                String rootcert = props.getProperty("connection.sslrootcert");
                PostgisNGDataStoreFactory factory = new SSLEnabledPostgisNGDataStoreFactory(); // new PostgisNGDataStoreFactory();
                Map<String, Object> params = new HashMap<>();
                params.put("host", host);
                params.put("port", port);
                params.put("database", DB_NAME);
                params.put("schema", SCHEMA_NAME);
                params.put("user", user);
                params.put("passwd", passwd);
                params.put("dbtype", "postgis");
                params.put("wkb enabled", true);
                if (sslMode != null) {
                    params.put("sslmode", sslMode);
                }
                if (cert != null) {
                    params.put("sslcert", cert);
                }
                if (key != null) {
                    params.put("sslkey", key);
                }
                if (rootcert != null) {
                    params.put("sslrootcert", rootcert);
                }
                _dataStore = factory.createDataStore(params);
            }
        }
        return _dataStore;
    }

    /**
     * @throws IOException
     * @throws MissingLocalMapsException
     *
     */
    public void open() throws IOException, MissingLocalMapsException {
        DataStore dataStore = getDataStore();
        try {
            schema = dataStore.getSchema(this.tableName);
        } catch (IOException e) {
            throw new MissingLocalMapsException(e);
        }

        shapeField = schema.getGeometryDescriptor().getLocalName();
        featureCollection = null;
        featureIterator = null;

        query = new Query();
        query.setTypeName(this.tableName);
        List<String> propNames = new ArrayList<String>(getAttributeNames());
        propNames.add(shapeField);
        query.setPropertyNames(propNames);

        FilterFactory2 ff = CommonFactoryFinder
                .getFilterFactory2(GeoTools.getDefaultHints());

        Filter filter = null;
        if (boundingGeom != null) {
            filter = ff.intersects(ff.property(shapeField),
                    ff.literal(boundingGeom));
        }

        if (boundingBox != null) {
            filter = ff.bbox(ff.property(shapeField), boundingBox);
        }

        if (filter != null) {
            query.setFilter(filter);
        }

        SimpleFeatureSource featureSource = dataStore
                .getFeatureSource(this.tableName);
        featureCollection = featureSource.getFeatures(query);
        featureIterator = featureCollection.features();
    }

    /**
     * Returns the database table name
     *
     * @return the database table name
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Close the DbShapeSource
     *
     * @throws IOException
     *
     */
    public void close() throws IOException {
        if (featureIterator != null) {
            featureIterator.close();
            featureIterator = null;
            featureCollection = null;
        }
    }

    /**
     * Check if DbShapeSource has another feature
     *
     * @return true if DbShapeSource has another feature
     * @throws IOException
     */
    public boolean hasNext() throws IOException {
        if (featureIterator == null) {
            throw new IOException("DataStore is not open");
        }
        return featureIterator.hasNext();
    }

    /**
     * Return the next feature
     *
     * @return the next feature
     * @throws NoSuchElementException
     *             if there are no more features
     * @throws IOException
     *             if dataStore as not been opened
     */
    public SimpleFeature next() throws NoSuchElementException, IOException {
        if (featureIterator == null) {
            throw new IOException("DataStore is not open");
        }
        return featureIterator.next();
    }

    /**
     * Get the ShapeType of this DbShapeSource
     *
     * @return the ShapeType for the associated database table
     * @throws IOException
     *             if there are errors accessing the database table
     * @throws MissingLocalMapsException
     *             if table is not defined
     */
    public synchronized ShapeType getShapeType()
            throws IOException, MissingLocalMapsException {
        if (this.type == null) {
            boolean closeIt = false;
            if (schema == null) {
                open();
                closeIt = true;
            }

            Class<?> geometryType = schema.getGeometryDescriptor().getType()
                    .getBinding();

            if ((geometryType == Point.class)
                    || (geometryType == MultiPoint.class)) {
                this.type = ShapeType.POINT;
            } else if ((geometryType == LineString.class)
                    || (geometryType == MultiLineString.class)) {
                this.type = ShapeType.POLYLINE;
            } else if ((geometryType == Polygon.class)
                    || (geometryType == MultiPolygon.class)) {
                this.type = ShapeType.POLYGON;
            } else {
                this.type = ShapeType.NONE;
            }

            if (closeIt) {
                close();
            }
        }
        return this.type;
    }

    /**
     * Get the list of attribute names for this table
     *
     * @return the list of attribute names
     */
    public synchronized List<String> getAttributeNames() {
        if (attributeNames == null) {
            List<AttributeDescriptor> attrDesc = schema
                    .getAttributeDescriptors();
            if ((attrDesc == null) || (attrDesc.size() == 0)) {
                return null;
            }

            attributeNames = new ArrayList<String>(attrDesc.size());
            for (AttributeDescriptor at : attrDesc) {
                Class<?> atType = at.getType().getBinding();
                if (!Geometry.class.isAssignableFrom(atType)) {
                    attributeNames.add(at.getLocalName());
                }
            }
        }
        return attributeNames;
    }

    /**
     * Get the attributes for a feature
     *
     * @param feature
     * @return a Map of attribute names to values
     * @throws IOException
     */
    public Map<String, Object> getAttributes(SimpleFeature feature)
            throws IOException {
        Map<String, Object> retVal = new HashMap<String, Object>();
        for (String at : getAttributeNames()) {
            Object attr = feature.getAttribute(at);
            if (attr != null) {
                retVal.put(at, attr);
            }
        }

        return retVal;
    }

    @Override
    public String toString() {
        return this.getTableName();
    }

    /**
     * @return the filter
     */
    public boolean isFiltered() {
        return this.filtered;
    }

    /**
     * Set flag indicating this object is filtered
     *
     * @param filtered
     *            true if filtered
     */
    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
    }

    /**
     * Set bounding Geometry for database query
     *
     * @param geom
     *            the bounding geometry
     */
    public void setBoundingGeometry(Geometry geom) {
        this.boundingGeom = geom;
    }

    /**
     * Set bounding box for database query
     *
     * @param bbox
     *            the bounding box
     */
    public void setBoundingBox(BoundingBox bbox) {
        this.boundingBox = bbox;
    }

    /**
     * Get the feature count
     *
     * @return the feature count
     * @throws IOException
     *             if the dataStore was not successfully opened or has been
     *             closed
     */
    public int getFeatureCount() throws IOException {
        if (this.featureCollection == null) {
            throw new IOException(
                    "DataStore must be open when calling getFeatureCount");
        }
        return this.featureCollection.size();
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName
     *            the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     *            the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return true if has edit area name
     */
    public boolean hasEditAreaName() {
        return this.hasEditAreaName;
    }

    /**
     * @param hasEditAreaName
     *            true if has edit area name
     */
    public void setHasEditAreaName(boolean hasEditAreaName) {
        this.hasEditAreaName = hasEditAreaName;
    }

    /**
     * @return the instanceName
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * @param instanceName
     *            the instanceName to set
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * Return date the database table was last updated
     *
     * @return date of last update
     * @throws MissingLocalMapsException
     */
    public Date getLastUpdated() throws MissingLocalMapsException {
        String sqlQuery = "SELECT import_time FROM " + SCHEMA_NAME
                + ".map_version WHERE table_name = :tableName";
        try {
            CoreDao dao = new CoreDao(DaoConfig.forDatabase(DB_NAME));
            Object[] result = dao.executeSQLQuery(sqlQuery, "tableName",
                    this.tableName);
            if (result.length < 1) {
                throw new GfeConfigurationException(
                        "Missing database table for map " + tableName);
            }
            return (Date) result[0];
        } catch (Exception e) {
            throw new MissingLocalMapsException(e);
        }
    }
}
