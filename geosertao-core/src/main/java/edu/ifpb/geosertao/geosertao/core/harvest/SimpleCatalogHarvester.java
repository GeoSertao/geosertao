package edu.ifpb.geosertao.geosertao.core.harvest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import edu.ifpb.geosertao.geosertao.core.entities.CatalogService;
import br.geosertao.entities.MetadataRecord;
import br.geosertao.dao.CatalogServiceDaoIF;
import br.geosertao.dao.DaoFactoryBuilder;
import br.geosertao.dao.relational.CatalogServiceDao;
import br.geosertao.dao.MetadataRecordDaoIF;
import org.geotoolkit.csw.CatalogServicesServer;
import org.geotoolkit.csw.GetRecordsRequest;
import org.geotoolkit.csw.xml.CSWMarshallerPool;
import org.geotoolkit.csw.xml.ElementSetType;
import org.geotoolkit.csw.xml.ResultType;
import org.geotoolkit.csw.xml.v202.GetRecordsResponseType;
import org.geotoolkit.csw.xml.v202.RecordType;
import org.geotoolkit.xml.MarshallerPool;
import org.jdom.JDOMException;

/**
 *
 * @author Fabio
 */
public class SimpleCatalogHarvester {

    public static final String CSW_SERVICE_VERSION = "2.0.2";
    public static final int BATCH_SIZE = 500;
    private String url;
    private CatalogServicesServer cswServer;
    private CatalogService cswService;

    public SimpleCatalogHarvester(String url) {
        this.url = url;
    }

    public SimpleCatalogHarvester(CatalogService cswService) {
        this.url = cswService.getUrl();
        this.cswService = cswService;
    }

    private void search() throws MalformedURLException, IOException, JAXBException, JDOMException, SQLException {
        cswServer = new CatalogServicesServer(new URL(url), CSW_SERVICE_VERSION);

        MarshallerPool pool = CSWMarshallerPool.getInstance();
        Unmarshaller um = pool.acquireUnmarshaller();

        GetRecordsRequest request = createGetRecordRequest(cswServer);

        System.out.println("Sending first query");
        System.out.println("Request URL:" + request.getURL());
        InputStream is = request.getResponseStream();

        int numberOfRecords = 0;
        GetRecordsResponseType response = null;

        try {
            response = ((JAXBElement<GetRecordsResponseType>) um.unmarshal(is)).getValue();
            System.out.println("Response:" + response);
            System.out.println("Number of matched records: " + response.getSearchResults().getNumberOfRecordsMatched());
            numberOfRecords = response.getSearchResults().getNumberOfRecordsMatched();
            System.out.println("Number of records: ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int cont = 0;
        int recordCount = 0;
        int numberOfReturnedRecords = 0;
        List<MetadataInformationIF> result = new ArrayList();

        do {
            System.out.println("Requesting from " + cont);
            request = createGetRecordRequest(cswServer);
            request.setStartPosition(cont + 1);
            try {
                is = request.getResponseStream();
                response = ((JAXBElement<GetRecordsResponseType>) um.unmarshal(is)).getValue();
                System.out.println("Number of returned records:" + response.getSearchResults().getAbstractRecord().size());
                numberOfReturnedRecords = response.getSearchResults().getAbstractRecord().size();
                cont += numberOfReturnedRecords;
                System.out.println("Cont -> " + cont);

                Iterator metadataRecords = response.getSearchResults().getAbstractRecord().iterator();
                MetadataRecordDaoIF dao = DaoFactoryBuilder.createDaoFactory().createMetadataRecordDao();
                while (metadataRecords.hasNext()) {
                    RecordType currentRecord = (RecordType) metadataRecords.next();
                    recordCount++;
                    System.out.println("Processing record " + recordCount + " of " + numberOfRecords);
                    try {
                        if (!dao.exists(currentRecord.getIdentifierStringValue(), cswService.getId())) {
                            MetadataRecord record = new MetadataRecord();
                            record.setMetadataIdentifier(currentRecord.getIdentifierStringValue());
                            record.setCatalog(cswService);
                            dao.save(record);
                        }
                    } catch (Exception e) {
                        //  System.out.println("Exception "+e);
                        // e.printStackTrace();
                    }
                }

            } catch (Exception e) {//System.out.println("Exception "+e);
                // e.printStackTrace();}
            }

        } while ((cont < numberOfRecords) && (numberOfReturnedRecords > 0));

        System.out.println("Done");

    }

    private GetRecordsRequest createGetRecordRequest(CatalogServicesServer cswServer) {
        GetRecordsRequest request = cswServer.createGetRecords();
        request.setTypeNames("csw:Record");
        request.setResultType(ResultType.RESULTS);
        request.setConstraintLanguage("CQL");
        request.setConstraintLanguageVersion("1.1.0");
        request.setElementSetName(ElementSetType.FULL);
        request.setMaxRecords(BATCH_SIZE);
        return request;
    }

    public static void main(String[] args) {
        try {
            CatalogServiceDaoIF cswDao = new CatalogServiceDao();
            Iterator<CatalogService> cswServices = cswDao.list().iterator();
            while (cswServices.hasNext()) {
                CatalogService csw = cswServices.next();
                if (csw.getId() == 8) {
                    System.out.println("Csw service -> " + csw.getUrl());
                    SimpleCatalogHarvester cswCrawler = new SimpleCatalogHarvester(csw);
                    cswCrawler.search();
                }

            }
        } catch (Exception e) {
            // e.printStackTrace();

        }
    }

}
