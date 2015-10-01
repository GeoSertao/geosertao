package edu.ifpb.geosertao.geosertao.core.harvest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.fao.geonet.csw.common.exceptions.CatalogException;
import org.fao.geonet.csw.common.requests.GetRecordsRequest;
import br.geosertao.entities.CatalogService;
import org.fao.geonet.csw.common.ResultType;
import org.fao.geonet.csw.common.ElementSetName;
import br.geosertao.dao.CatalogServiceDaoIF;
import br.geosertao.dao.relational.CatalogServiceDao;
import org.geotoolkit.csw.xml.CSWMarshallerPool;
import org.geotoolkit.csw.xml.v202.GetRecordsResponseType;
import org.geotoolkit.xml.MarshallerPool;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author Fabio
 */
public class NewMetadataRecordCrawler {

    public static final String CSW_SERVICE_VERSION = "2.0.2";
    public static final String BATCH_SIZE = "500";
    private String url;
    private CatalogService cswService;

    public NewMetadataRecordCrawler(String url) {
        this.url = url;
    }

    public NewMetadataRecordCrawler(CatalogService cswService) {
        this.url = cswService.getUrl();
        this.cswService = cswService;
    }

    private void search() throws IOException, JAXBException, CatalogException, JDOMException, Exception {
        /*    MarshallerPool pool = CSWMarshallerPool.getInstance();
         Unmarshaller um = pool.acquireUnmarshaller();


         GetRecordsRequest request = createGetRecordRequest(url);
         Element response = request.execute();


         System.out.println("Sending first query");

         GetRecordsResponseType responseType = ((JAXBElement<GetRecordsResponseType>) um.ununmarshal()).getValue();
         System.out.println("Number of matched records:"+response.getSearchResults().getNumberOfRecordsMatched());
         int numberOfRecords = response.getSearchResults().getNumberOfRecordsMatched();



         int cont = 1;
         int recordCount = 0;
         List<MetadataInformationIF> result = new ArrayList();

         do{
         System.out.println("Requesting from "+cont);
         request = createGetRecordRequest(cswServer);
         request.setStartPosition(cont+1);
         try{
         is = request.getResponseStream();
         response = ((JAXBElement<GetRecordsResponseType>) um.unmarshal(is)).getValue();
         System.out.println("Number of returned records:"+response.getSearchResults().getAbstractRecord().size());
         int numberOfReturnedRecords = response.getSearchResults().getAbstractRecord().size();


         Iterator metadataRecords = response.getSearchResults().getAbstractRecord().iterator();
         MetadataRecordDao dao = new MetadataRecordDaoImpl();
         while(metadataRecords.hasNext()){
         SummaryRecordType currentRecord = (SummaryRecordType) metadataRecords.next();
         recordCount++;
         System.out.println("Processing record "+recordCount);
         try{

         MetadataRecord record = new MetadataRecord();
         record.setMetadataIdentifier(currentRecord.getIdentifierStringValue());
         record.setCatalog(cswService);
         dao.save(record);
         }
         catch(Exception e)
         {//System.out.println("Exception "+e);
         //e.printStackTrace();}
         }
         }
         cont+=numberOfReturnedRecords;
         }
         catch(Exception e){}





         }
         while(cont<numberOfRecords);



         System.out.println("Done");*/

    }

    private GetRecordsRequest createGetRecordRequest(String url) throws MalformedURLException {
        GetRecordsRequest request = new GetRecordsRequest();
        request.setUrl(new URL(url));
        request.setResultType(ResultType.RESULTS);
        request.setElementSetName(ElementSetName.BRIEF);
        //request.setOutputSchema("Record");
        request.setMaxRecords(BATCH_SIZE);
        return request;
    }

}
