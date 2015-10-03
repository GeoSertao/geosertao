
package edu.ifpb.geosertao.geosertao.core.harvest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import br.geosertao.entities.BoundingBox;
import br.geosertao.entities.BoundingBoxIF;
import br.geosertao.entities.MetadataRecord;
import br.geosertao.entities.MetadataRecordUrl;
import br.geosertao.util.CollectionManager;
import org.geotoolkit.metadata.iso.extent.DefaultExtent;
import org.geotoolkit.service.ServiceIdentificationImpl;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.distribution.DigitalTransferOptions;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.extent.TemporalExtent;
import org.opengis.metadata.identification.DataIdentification;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.identification.KeywordType;
import org.opengis.metadata.identification.Keywords;
import org.opengis.metadata.identification.Progress;
import org.opengis.metadata.quality.DataQuality;


/**
 *
 * @author Fabio
 */
public class IsoMetadataRecordParser {

        public static final int GEOGRAPHIC_EXTENT = 0;
        public static final int TEMPORAL_EXTENT = 1;
        public static final int VERTICAL_EXTENT = 2;
        public static final String CREATION_DATE = "creation";
        public static final String PUBLICATION_DATE = "publication";
        public static final String REVISION_DATE = "revision";


        public static MetadataRecord parse(Metadata metadata, MetadataRecord record){
                 record.setName(getTitle(metadata));
                 record.setMetadataIdentifier(getMetadataIdentifier(metadata));
                 record.setTextDescription(getAbstract(metadata));
                 record.setPublisher(getPublisher(metadata));

                 Collection<String> keywords = getKeywords(metadata);
                 record.setKeywords(CollectionManager.getCollectionAsString(keywords, ", "));

                 record.setCoveredArea(getCoveredArea(metadata));
                 record.setTemporalExtent(getTemporalExtent(metadata));

                 Collection<MetadataRecordUrl> links = new ArrayList();

                 Iterator<String> linksIt = getLinks(metadata).iterator();
                 while(linksIt.hasNext()){
                       MetadataRecordUrl link = new MetadataRecordUrl();
                       link.setUrl(linksIt.next());
                       links.add(link);
                 }


                 record.setUrls(links);


                 record.setUrl(getUrl(metadata));
                 record.setPublicationDate(getPublicationDate(metadata));
                 record.setCreationDate(getDate(metadata, CREATION_DATE));
                 record.setRevisionDate(getDate(metadata, REVISION_DATE));
                 record.setUpdate(getStatus(metadata));
                 record.setLineage(getLineage(metadata));
                 record.setLanguage(getLanguage(metadata));

                 return record;

        }



           public static MetadataRecord parse(Metadata metadata){
                 return parse(metadata, new MetadataRecord());
           }


            private static String getPublisher(Metadata record){
                String publisher = null;
                try{
                       Identification idInfo = record.getIdentificationInfo().iterator().next();
                       ResponsibleParty rp = idInfo.getPointOfContacts().iterator().next();
                       publisher = idInfo.getPointOfContacts().iterator().next().getOrganisationName().toString();

                }
                catch(Exception e){}

                return publisher;
            }


            private static String getStatus(Metadata record){
                  String status = null;
                  try{
                      Identification idInfo = record.getIdentificationInfo().iterator().next();
                      Collection<Progress> progressInf = idInfo.getStatus();
                      if(progressInf!=null){
                           Iterator<Progress> progressIt = progressInf.iterator();
                           while(progressIt.hasNext()){
                                Progress statusProgress = progressIt.next();
                                return statusProgress.name();
                           }
                      }
                }
                catch(Exception e){}
                  return null;

            }

             private static String getUrl(Metadata record){
                String url = null;
                try{
                       Identification idInfo = record.getIdentificationInfo().iterator().next();
                       ResponsibleParty rp = idInfo.getPointOfContacts().iterator().next();
                       url = idInfo.getPointOfContacts().iterator().next().getContactInfo().getOnlineResource().getLinkage().toString();
                       return url;

                }
                catch(Exception e){}
                Collection<String> links =  getLinks(record);
                if(! links.isEmpty())
                    return links.iterator().next();
                
                return url;
            }

            private static java.sql.Date getDate(Metadata record, String dateType){
                 try{
                       Identification idInfo = record.getIdentificationInfo().iterator().next();
                       Collection<? extends CitationDate> citDates = idInfo.getCitation().getDates();
                       if(citDates!=null){
                            Iterator<? extends CitationDate> dates = citDates.iterator();
                            while(dates.hasNext()){
                                 CitationDate date = dates.next();
                                 if(date.getDateType().toString().equals(dateType))
                                      return new java.sql.Date(date.getDate().getTime());
                            }
                      }
                 }
                 catch(Exception e){}
                 return null;
            }

            private static String getTitle(Metadata record){
                String title = null;

                try{
                     Identification idInfo = record.getIdentificationInfo().iterator().next();
                     title = idInfo.getCitation().getTitle().toString();
                }
                catch(Exception e){}

                return title;
            }

            private static String getAbstract(Metadata record){
                String textDescription = null;

                try{
                      Identification idInfo = record.getIdentificationInfo().iterator().next();
                      textDescription = idInfo.getAbstract().toString();
                }
                catch(Exception e){}

                return textDescription;
            }

            private static Collection<String> getKeywords(Metadata meta){
                  
                  Collection<String> result = new ArrayList();
                  try{
                      Identification idInfo = meta.getIdentificationInfo().iterator().next();
                      Collection<? extends Keywords> keywords = idInfo.getDescriptiveKeywords();

                      if(keywords!=null){
                          Iterator<Keywords> it = (Iterator<Keywords>) keywords.iterator();
                          while(it.hasNext()){
                               Keywords keyword = it.next();
                               if((keyword.getKeywords()!=null)&&(keyword.getType()==KeywordType.THEME)){
                                   Iterator words = keyword.getKeywords().iterator();
                                   while(words.hasNext()){
                                        result.add(words.next().toString());
                                   }
                               }
                          }
                      }

                      

                      

                  }
                  catch(Exception e)
                  {}
                  return result;
            }


            private static Collection<String> getLinks(Metadata meta){
                  Collection<String> result = new ArrayList();
                  try{
                      Distribution distInfo = meta.getDistributionInfo();
                      Collection<? extends DigitalTransferOptions> transferOptions = distInfo.getTransferOptions();
                     if(transferOptions!=null){
                            Iterator<? extends DigitalTransferOptions> transferOptionsIt = transferOptions.iterator();
                            while(transferOptionsIt.hasNext()){
                                 DigitalTransferOptions transfOption = transferOptionsIt.next();
                                 Collection<? extends OnlineResource> onlResources = transfOption.getOnLines();
                                 if(onlResources!=null){
                                       Iterator<? extends OnlineResource> onlResourcesIt = onlResources.iterator();
                                       while(onlResourcesIt.hasNext()){
                                             OnlineResource resource = onlResourcesIt.next();
                                             if(resource.getLinkage()!=null){
                                                   result.add(resource.getLinkage().toString());
                                             }
                                       }
                                 }

                            }
                      }
                  }
                  catch (Exception e)
                  {}
                  return result;
            }


            private static String getMetadataIdentifier(Metadata record){
                String metadataIdentifier =  metadataIdentifier = record.getFileIdentifier();

                if(metadataIdentifier==null){
                     try{
                          metadataIdentifier = record.getIdentificationInfo().iterator().next().getCitation().getIdentifiers().iterator().next().toString();
                     }
                     catch(Exception e){}
                }

                return metadataIdentifier;
            }

            private static String getLineage(Metadata record){
                String result = "";
                try{
                      Collection<? extends DataQuality> qualityList =  record.getDataQualityInfo();
                      Iterator<? extends DataQuality> qualityIt = qualityList.iterator();
                      while(qualityIt.hasNext()){
                            DataQuality qualityInfo = qualityIt.next();
                            result += qualityInfo.getLineage().getStatement().toString();
                      }
                }
                catch(Exception e)
                {}
                return result;

            }

            private static BoundingBoxIF getCoveredArea(Metadata record){
                  try{
                         DataIdentification identificationInfo = (DataIdentification) record.getIdentificationInfo().iterator().next();

                         DefaultExtent obj = (DefaultExtent) identificationInfo.getExtents().iterator().next();

                         Collection geographicExtentCol = getExtent(identificationInfo.getExtents(), GEOGRAPHIC_EXTENT);
                         GeographicBoundingBox geographicExt = (GeographicBoundingBox) geographicExtentCol.iterator().next();
                         double minX = geographicExt.getWestBoundLongitude();
                         double minY = geographicExt.getSouthBoundLatitude();
                         double maxX = geographicExt.getEastBoundLongitude();
                         double maxY = geographicExt.getNorthBoundLatitude();

                         return new BoundingBox(minX, minY, maxX, maxY);

                  }
                  catch(Exception e)
                  {}

                  try{
                          ServiceIdentificationImpl identificationInfo = ( ServiceIdentificationImpl) record.getIdentificationInfo().iterator().next();

                         DefaultExtent obj = (DefaultExtent) identificationInfo.getExtent().iterator().next();

                         Collection geographicExtentCol = getExtent(identificationInfo.getExtent(), GEOGRAPHIC_EXTENT);
                         GeographicBoundingBox geographicExt = (GeographicBoundingBox) geographicExtentCol.iterator().next();
                         double minX = geographicExt.getWestBoundLongitude();
                         double minY = geographicExt.getSouthBoundLatitude();
                         double maxX = geographicExt.getEastBoundLongitude();
                         double maxY = geographicExt.getNorthBoundLatitude();

                         return new BoundingBox(minX, minY, maxX, maxY);

                  }
                  catch(Exception e){e.printStackTrace();}

                  return null;
            }

             private static String getTemporalExtent(Metadata record){
                  try{
                          ServiceIdentificationImpl identificationInfo = ( ServiceIdentificationImpl) record.getIdentificationInfo().iterator().next();

                         DefaultExtent obj = (DefaultExtent) identificationInfo.getExtent().iterator().next();

                         Collection temporalExtentCol = getExtent(identificationInfo.getExtent(), TEMPORAL_EXTENT);
                         if(temporalExtentCol.size()==0)
                              return null;

                         TemporalExtent temporalExt = (TemporalExtent) temporalExtentCol.iterator().next();

                         if(temporalExt.getExtent()!=null)
                            return temporalExt.getExtent().toString();


                  }
                  catch(Exception e){ }

                  try{
                         DataIdentification identificationInfo = (DataIdentification) record.getIdentificationInfo().iterator().next();

                         DefaultExtent obj = (DefaultExtent) identificationInfo.getExtents().iterator().next();

                         Collection temporalExtentCol = getExtent(identificationInfo.getExtents(), TEMPORAL_EXTENT);
                         if(temporalExtentCol.size()==0)
                              return null;

                         TemporalExtent temporalExt = (TemporalExtent) temporalExtentCol.iterator().next();

                         if(temporalExt.getExtent()!=null)
                            return temporalExt.getExtent().toString();


                  }
                  catch(Exception e){ }
                  return null;
            }



            private static Collection getExtent (Collection<? extends Extent> extentList, int extentType){
                  if(extentList==null)
                       return null;
                  Iterator<? extends Extent> extentIt = extentList.iterator();
                  while(extentIt.hasNext()){
                        DefaultExtent extent = (DefaultExtent) extentIt.next();
                        if(extentType==GEOGRAPHIC_EXTENT){
                             if(extent.getGeographicElements()!=null){
                                  return extent.getGeographicElements();
                             }
                        }
                        if(extentType==TEMPORAL_EXTENT){
                             if(extent.getTemporalElements()!=null){
                                  return extent.getTemporalElements();
                             }
                        }
                        if(extentType==VERTICAL_EXTENT){
                             if(extent.getVerticalElements()!=null){
                                  return extent.getVerticalElements();
                             }
                        }
                  }
                  return null;
           }

           private static String getLanguage(Metadata metadata) {
                 try{
                      return metadata.getLanguage().toString();
                 }
                 catch(Exception e)
                 {}
                  try{
                      DataIdentification identificationInfo = (DataIdentification) metadata.getIdentificationInfo().iterator().next();
                      return identificationInfo.getLanguages().iterator().next().toString();
                 }
                 catch(Exception e)
                 {}
                 return null;
           }


           private static java.sql.Date getPublicationDate(Metadata record){
                 Date publicationDate = getDate(record, PUBLICATION_DATE);
                 if(publicationDate!=null)
                      return new java.sql.Date(publicationDate.getTime());
                 if(record.getDateStamp()!=null)
                      return new java.sql.Date(record.getDateStamp().getTime());
                 return null;

            }

      


       
}
