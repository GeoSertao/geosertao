package edu.ifpb.geosertao.geosertao.core.harvest;

import br.geosertao.entities.BoundingBox;
import br.geosertao.entities.BoundingBoxIF;
import br.geosertao.entities.MetadataRecord;
import br.geosertao.entities.MetadataRecordUrl;
import br.geosertao.util.CollectionManager;
import br.geosertao.util.DateManager;
import br.geosertao.util.StringManager;
import br.geosertao.util.XmlParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.jdom.Element;

public class IsoMetadataParser {

    public static final String SPATIAL_EXTENT = "geographicElement";
    public static final String TEMPORAL_EXTENT = "temporalElement";
    public static final String CREATION_DATE = "creation";
    public static final String PUBLICATION_DATE = "publication";
    public static final String REVISION_DATE = "revision";

    public static MetadataRecord parse(Element root) {
        MetadataRecord record = new MetadataRecord();
        record.setPublisher(getPublisher(root));
        record.setName(getTitle(root));
        record.setTextDescription(getTextDescription(root));
        record.setLanguage(getLanguage(root));
        record.setMetadataIdentifier(getIdentifier(root));
        record.setUrl(getURL(root));
        record.setUrls(getUrls(root));
        record.setLineage(getLineage(root));

        Collection keywords = getKeywords(root);
        record.setKeywords(CollectionManager.getCollectionAsString(keywords, ","));
        record.setCoveredArea(getSpatialExtent(root));
        getDate(root, SPATIAL_EXTENT);

        String publicationDate = getPublicationDate(root);

        if (publicationDate != null) {
            java.util.Date date = DateManager.parse(publicationDate);
            if (date != null) {
                record.setPublicationDate(new java.sql.Date(date.getTime()));
            }
        }

        String revisionDate = getDate(root, REVISION_DATE);
        if (revisionDate != null) {
            java.util.Date date = DateManager.parse(revisionDate);
            if (date != null) {
                record.setRevisionDate(new java.sql.Date(date.getTime()));
            }
        }

        String creationDate = getDate(root, CREATION_DATE);
        if (creationDate != null) {
            java.util.Date date = DateManager.parse(creationDate);
            if (date != null) {
                record.setPublicationDate(new java.sql.Date(date.getTime()));
            }
        }

        return record;
    }

    private static String getTitle(Element root) {
        String title = XmlParser.getElementValue(root, "idinfo/citation/citeinfo/title");
        if (title == null) {
            title = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/citation/CI_Citation/title");
        }
        if (title == null) {
            title = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/citation/CI_Citation/title/CharacterString");
        }
        if (title == null) {
            title = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/citation/CI_Citation/title/CharacterString");
        }
        return title;
    }

    private static String getIdentifier(Element root) {
        String id = XmlParser.getElementValue(root, "fileIdentifier");
        if (id == null) {
            id = XmlParser.getElementValue(root, "fileIdentifier/CharacterString");
        }
        if (id == null) {
            id = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/citation/CI_Citation/title/CharacterString");
        }
        if (id == null) {
            id = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/citation/CI_Citation/title/CharacterString");
        }

        return id;
    }

    private static String getPublisher(Element root) {
        String publisherName = XmlParser.getElementValue(root, "idinfo/citation/citeinfo/pubinfo/publish");
        if (publisherName == null) {
            publisherName = XmlParser.getElementValue(root, "contact/CI_ResponsibleParty/organisationName/CharacterString");
        }
        if (publisherName == null) {
            publisherName = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/pointOfContact/CI_ResponsibleParty/organisationName");
        }
        if (publisherName == null) {
            publisherName = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/pointOfContact/CI_ResponsibleParty/organisationName");
        }
        if (publisherName == null) {
            publisherName = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/citation/CI_Citation/citedResponsibleParty/CI_ResponsibleParty/contactInfo/CI_Contact/onlineResource/CI_OnlineResource/name");
        }
        if (publisherName == null) {
            publisherName = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/citation/CI_Citation/citedResponsibleParty/CI_ResponsibleParty/contactInfo/CI_Contact/onlineResource/CI_OnlineResource/name");
        }
        if (publisherName == null) {
            publisherName = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/citation/CI_Citation/citedResponsibleParty/CI_ResponsibleParty/contactInfo/CI_Contact/onlineResource/CI_OnlineResource/name");
        }
        if (publisherName == null) {
            publisherName = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/citation/CI_Citation/citedResponsibleParty/CI_ResponsibleParty/organisationName/CharacterString");
        }
        if (publisherName == null) {
            publisherName = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/citation/CI_Citation/citedResponsibleParty/CI_ResponsibleParty/organisationName/CharacterString");
        }

        return publisherName;
    }

    private static String getTextDescription(Element root) {
        String text = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/idinfo/descript/abstract");
        if (text == null) {
            text = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/abstract");
        }
        if (text == null) {
            text = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/abstract");
        }
        return text;
    }

    private static String getLanguage(Element root) {
        String language = XmlParser.getElementValue(root, "language");
        if (language == null) {
            language = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/language");
        }
        if (language == null) {
            language = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/language");
        }

        return language;
    }

    private static String getPublicationDate(Element root) {
        String date = XmlParser.getElementValue(root, "dateStamp");
        if (date == null) {
            date = XmlParser.getElementValue(root, "dateStamp/Date");
        }
        if (date == null) {
            date = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/idinfo/citation/citeinfo/pubdate");
        }
        if (date == null) {
            date = XmlParser.getElementValue(root, "identificationInfo/MD_DataIdentification/citation/CI_Citation/date/CI_Date/date/Date");
        }
        if (date == null) {
            date = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/idinfo/citation/citeinfo/pubdate");
        }
        if (date == null) {
            date = XmlParser.getElementValue(root, "identificationInfo/SV_ServiceIdentification/citation/CI_Citation/date/CI_Date/date/Date");
        }

        return date;
    }

    private static Collection<String> getKeywords(Element root) {
        Collection<String> keywords = new ArrayList();
        Collection<Element> keywordsList = XmlParser.findElementSetByPath(root, "identificationInfo/MD_DataIdentification/idinfo/keywords/theme");
        if (keywordsList != null) {
            Iterator<Element> it = keywordsList.iterator();
            while (it.hasNext()) {
                Element element = it.next();
                Collection<String> themeKeywords = XmlParser.getElementSetValue(element, "themekey");
                if (themeKeywords != null) {
                    Iterator<String> words = themeKeywords.iterator();
                    while (words.hasNext()) {
                        String keyword = words.next();
                        keywords.add(StringManager.remove(keyword, '\n'));

                    }
                }

            }
        }
        keywordsList = XmlParser.findElementSetByPath(root, "identificationInfo/MD_DataIdentification/descriptiveKeywords");
        if (keywordsList != null) {
            Iterator<Element> it = keywordsList.iterator();
            while (it.hasNext()) {
                Element element = it.next();
                Element mdKeyword = XmlParser.findElementByPath(element, "MD_Keywords");
                Collection<Element> kw = XmlParser.findElementSetByPath(mdKeyword, "keyword");
                if (kw != null) {
                    Iterator<Element> kwIt = kw.iterator();
                    while (kwIt.hasNext()) {
                        Element key = kwIt.next();
                        if ((key.getValue() != null) && (!key.getValue().isEmpty())) {
                            keywords.add(StringManager.remove(key.getValue(), '\n'));
                        } else {
                            String value = XmlParser.getElementValue(key, "CharacterString");
                            if (value != null) {
                                keywords.add(StringManager.remove(value, '\n'));
                            }
                        }

                    }

                }
            }
        }
        keywords.addAll(getTopicCategories(root));
        return keywords;
    }

    private static BoundingBoxIF getSpatialExtent(Element element) {
        try {
            Element geoExtent = getExtent(element, SPATIAL_EXTENT);

            String minXPath = "EX_GeographicBoundingBox/westBoundLongitude/Decimal";
            Element minXElement = XmlParser.findElementByPath(geoExtent, minXPath);
            double minX = Double.parseDouble(minXElement.getValue().trim());

            String minYPath = "EX_GeographicBoundingBox/southBoundLatitude/Decimal";
            Element minYElement = XmlParser.findElementByPath(geoExtent, minYPath);
            double minY = Double.parseDouble(minYElement.getValue().trim());

            String maxXPath = "EX_GeographicBoundingBox/eastBoundLongitude/Decimal";
            Element maxXElement = XmlParser.findElementByPath(geoExtent, maxXPath);
            double maxX = Double.parseDouble(maxXElement.getValue().trim());

            String maxYPath = "EX_GeographicBoundingBox/northBoundLatitude/Decimal";
            Element maxYElement = XmlParser.findElementByPath(geoExtent, maxYPath);
            double maxY = Double.parseDouble(maxYElement.getValue().trim());

            return new BoundingBox(minX, minY, maxX, maxY);
        } catch (Exception e) {
        }
        return null;
    }

    private static Element getExtent(Element element, String type) {
        Collection<Element> extents = XmlParser.findElementSetByPath(element, "identificationInfo/MD_DataIdentification/extent/EX_Extent");
        if (extents == null) {
            extents = XmlParser.findElementSetByPath(element, "identificationInfo/SV_ServiceIdentification/extent/EX_Extent");
        }
        if (extents == null) {
            return null;
        }
        Iterator<Element> extentsIt = extents.iterator();
        while (extentsIt.hasNext()) {
            Element extent = extentsIt.next();
            if ((extent.getChildren() != null)) {
                Element child = (Element) extent.getChildren().get(0);
                if ((child != null) && (child.getName() != null)) {
                    if (child.getName().equals(type)) {
                        return child;
                    }
                }
            }
        }
        return null;
    }

    private static String getURL(Element element) {
        try {

            String path = "identificationInfo/MD_Distribution/transferOptions/MD_DigitalTransferOptions/onLine/CI_OnlineResource/linkage/URL";
            Element result = XmlParser.findElementByPath(element, path);
            return result.getValue();

        } catch (Exception e) {
        }
        return "";
    }

    private static Collection<MetadataRecordUrl> getUrls(Element element) {
        Collection<MetadataRecordUrl> result = new ArrayList();
        try {
            String path = "distributionInfo/MD_Distribution/transferOptions/MD_DigitalTransferOptions/onLine";
            Collection<Element> transfOptions = XmlParser.findElementSetByPath(element, path);
            Iterator<Element> transfOptionsIt = transfOptions.iterator();
            while (transfOptionsIt.hasNext()) {
                Element option = transfOptionsIt.next();
                String url = XmlParser.getElementValue(option, "CI_OnlineResource/linkage/URL");
                MetadataRecordUrl metaUrl = new MetadataRecordUrl();
                metaUrl.setUrl(url);
                result.add(metaUrl);
            }

            return result;
        } catch (Exception e) {
        }
        return result;
    }

    private static String getDate(Element element, String dateType) {
        try {
            String path = "identificationInfo/MD_DataIdentification/citation/CI_Citation/date/CI_Date";
            Collection<Element> dates = XmlParser.findElementSetByPath(element, path);
            if (dates == null) {
                dates = XmlParser.findElementSetByPath(element, "identificationInfo/SV_ServiceIdentification/citation/CI_Citation/date/CI_Date");
            }
            Iterator<Element> datesIt = dates.iterator();
            while (datesIt.hasNext()) {
                Element date = datesIt.next();
                String value = XmlParser.getElementValue(date, "date/Date");
                if (value == null) {
                    value = XmlParser.getElementValue(date, "date/DateTime");
                }

                String type = XmlParser.getElementValue(date, "dateType/CI_DateTypeCode");
                if ((type == null) || (type.isEmpty())) {
                    type = XmlParser.getAtributeValue(date, "dateType/CI_DateTypeCode", "codeListValue");
                }
                if (type.equals(dateType)) {
                    return value;
                }

            }

        } catch (Exception e) {
        }
        return null;

    }

    private static String getLineage(Element element) {
        try {
            String path = "dataQualityInfo/DQ_DataQuality/lineage/LI_Lineage/statement/CharacterString";
            String lineage = XmlParser.getElementValue(element, path);
            return lineage;
        } catch (Exception e) {
        }
        return null;
    }

    private static Collection<String> getTopicCategories(Element element) {
        Collection<String> result = new ArrayList();
        try {
            String path = "identificationInfo/MD_DataIdentification/topicCategory";
            Collection<Element> elements = XmlParser.findElementSetByPath(element, path);
            if (elements != null) {
                Iterator<Element> it = elements.iterator();
                while (it.hasNext()) {
                    Element ce = it.next();
                    result.add(StringManager.cleanString(ce.getValue().trim()));
                }
            }

            return result;
        } catch (Exception e) {
        }
        return result;
    }

}
