/*ry.
 * To change this template, choose Tools | Template
 * and open the template in the editor.
 */
package edu.ifpb.geosertao.geosertao.core.harvest;

import br.geosertao.clients.ogc.WfsServer;
import br.geosertao.clients.ogc.WmsServer;
import br.geosertao.dao.DaoFactoryIF;
import br.geosertao.dao.DaoFactoryBuilder;
import br.geosertao.dao.MetadataRecordDaoIF;
import br.geosertao.dao.ServiceDaoIF;
import br.geosertao.entities.BoundingBoxIF;
import br.geosertao.entities.FeatureType;
import br.geosertao.entities.MetadataRecord;
import br.geosertao.entities.MetadataRecordUrl;
import br.geosertao.entities.Service;
import java.util.Collection;
import java.util.Iterator;

/**
 * a
 *
 * @author Fabio
 */
public class ServiceCrawler {

    private MetadataRecord record;

    public ServiceCrawler(MetadataRecord record) {
        this.record = record;

    }

    public MetadataRecord getRecord() {
        return record;
    }

    public void setRecord(MetadataRecord record) {
        this.record = record;
    }

    public void crawl() {
        Collection<MetadataRecordUrl> urls = record.getUrls();

        if (urls != null) {
            Iterator<MetadataRecordUrl> urlsIt = urls.iterator();
            while (urlsIt.hasNext()) {
                MetadataRecordUrl url = urlsIt.next();
                try {
                    crawl(url.getUrl());
                } catch (ServiceAccessException e) {
                }
                
            }

        }
    }

    private void crawl(String url) throws ServiceAccessException {
        int type = UrlAnalyzer.classify(url);
        System.out.println("\n\nProcessing url " + url + "   " + UrlAnalyzer.getTypeAsString(type));
        switch (type) {
            case ResourceType.WEB_MAP_SERVICE: {
                String wmsUrl = UrlAnalyzer.getServiceUrl(url);
                String wfsUrl = wmsUrl.replaceAll("wms", "wfs");
                wfsUrl = wfsUrl.replaceAll("WMS", "WFS");
                doService(wmsUrl, ResourceType.WEB_MAP_SERVICE);
                doService(wfsUrl, ResourceType.WEB_FEATURE_SERVICE);
            }
            case ResourceType.WEB_FEATURE_SERVICE: {
                String wfsUrl = UrlAnalyzer.getServiceUrl(url);
                String wmsUrl = wfsUrl.replaceAll("wfs", "wms");
                wmsUrl = wmsUrl.replaceAll("WFS", "WMS");
                System.out.println("Wms Url - > " + wmsUrl);
                System.out.println("Wfs Url - > " + wfsUrl);
                doService(wmsUrl, ResourceType.WEB_MAP_SERVICE);
                doService(wfsUrl, ResourceType.WEB_FEATURE_SERVICE);
            }

        }

    }

    private void doService(String serviceUrl, int serviceType) throws ServiceAccessException {
        try {
            Service service = getService(serviceUrl, serviceType);
            if (service != null) {
                service.setCoveredArea(getCoveredArea(service));
                service.setMetadataRecord(getRecord());
                service.setMetadataIdentifier(getRecord().getMetadataIdentifier());
                if (service.getName() == null) {
                    service.setName(getRecord().getName());
                }
                if (service.getPublisher() == null) {
                    service.setPublisher(getRecord().getPublisher());
                }

                DaoFactoryIF factory = DaoFactoryBuilder.createDaoFactory();
                ServiceDaoIF serviceDao = factory.createServiceDao();
                System.out.println("Persisting service ");
                service.print(System.out);
                serviceDao.save(service);
            }

        } catch (ServiceAccessException e) {
            System.out.println("Error accessing " + serviceUrl);
        }
    }

    private Service getService(String url, int serviceType) throws ServiceAccessException {
        if (serviceType == ResourceType.WEB_MAP_SERVICE) {
            WmsServer wmsServer = new WmsServer(url);
            return wmsServer.get();
        }
        if (serviceType == ResourceType.WEB_FEATURE_SERVICE) {
            WfsServer wfsServer = new WfsServer(url);
            return wfsServer.get();
        }
        return null;
    }

    private BoundingBoxIF getCoveredArea(Service service) {
        Collection<FeatureType> featureTypes = service.getFeatureTypes();
        if (featureTypes == null) {
            return null;
        }
        if (featureTypes.isEmpty()) {
            return null;
        }

        BoundingBoxIF coveredArea = getFirst(featureTypes);
        if (coveredArea == null) {
            return null;
        }

        Iterator<FeatureType> it = featureTypes.iterator();
        while (it.hasNext()) {
            FeatureType currentFeatureType = it.next();
            BoundingBoxIF currentBox = currentFeatureType.getCoveredArea();
            if (currentBox != null) {
                if (currentBox.getMinX() < coveredArea.getMinX()) {
                    coveredArea.setMinX(currentBox.getMinX());
                }
                if (currentBox.getMinY() < coveredArea.getMinY()) {
                    coveredArea.setMinY(currentBox.getMinY());
                }
                if (currentBox.getMaxX() < coveredArea.getMaxX()) {
                    coveredArea.setMaxX(currentBox.getMaxX());
                }
                if (currentBox.getMaxY() < coveredArea.getMaxY()) {
                    coveredArea.setMaxY(currentBox.getMaxY());
                }
            }
        }

        return coveredArea;

    }

    private BoundingBoxIF getFirst(Collection<FeatureType> featureTypes) {
        Iterator<FeatureType> it = featureTypes.iterator();
        while (it.hasNext()) {
            FeatureType currentFeatureType = it.next();
            if (currentFeatureType.getCoveredArea() != null) {
                return currentFeatureType.getCoveredArea();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            MetadataRecordDaoIF recordDao = DaoFactoryBuilder.createDaoFactory().createMetadataRecordDao();
            int id = 227503;
            int maxId = 228293;
            do {
                id++;
                System.out.println("Processing id " + id + " of " + maxId);
                MetadataRecord record = recordDao.find(id);
                if (record != null) {
                    ServiceCrawler crawler = new ServiceCrawler(record);
                    crawler.crawl();
                }

            } while (id <= maxId);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
