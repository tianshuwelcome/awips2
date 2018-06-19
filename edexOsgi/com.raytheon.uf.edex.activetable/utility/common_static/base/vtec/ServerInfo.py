##
# This software was developed and / or modified by Raytheon Company,
# pursuant to Contract DG133W-05-CQ-1067 with the US Government.
# 
# U.S. EXPORT CONTROLLED TECHNICAL DATA
# This software product contains export-restricted data whose
# export/transfer/disclosure is restricted by U.S. law. Dissemination
# to non-U.S. persons whether in the United States or abroad requires
# an export license or other authorization.
# 
# Contractor Name:        Raytheon Company
# Contractor Address:     6825 Pine Street, Suite 340
#                         Mail Stop B8
#                         Omaha, NE 68106
#                         402.291.0100
# 
# See the AWIPS II Master Rights File ("Master Rights File.pdf") for
# further licensing information.
##

##
# This is a base file that is not intended to be overridden.
##



import xml
from xml.etree import ElementTree
from xml.etree.ElementTree import Element, SubElement
import IrtAccess

def extractServerInfo(xmlIncoming):
    sources = []
    irt = IrtAccess.IrtAccess("")
    xmlTree = ElementTree.ElementTree(ElementTree.XML(xmlIncoming))
    sourceE = xmlTree.find('source')
    for addressE in sourceE.getchildren():
        sourceServer = irt.decodeXMLAddress(addressE)
        if sourceServer is None:
            continue
        sources.append(irt.printServerInfo(sourceServer))
    return sources

    