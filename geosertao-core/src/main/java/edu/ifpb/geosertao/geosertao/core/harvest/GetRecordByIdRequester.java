package edu.ifpb.geosertao.geosertao.core.harvest;

import br.geosertao.entities.MetadataRecord;
import br.geosertao.util.XmlParser;
import java.io.InputStream;
import java.net.URL;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import org.fao.geonet.csw.common.util.Xml;
import org.geotoolkit.csw.CatalogServicesServer;
import org.geotoolkit.csw.GetRecordByIdRequest;
import org.geotoolkit.csw.xml.CSWMarshallerPool;
import org.geotoolkit.csw.xml.v202.GetRecordByIdResponseType;

import org.geotoolkit.xml.MarshallerPool;
import org.jdom.Element;
import org.opengis.metadata.Metadata;

public class GetRecordByIdRequester {

    private String cswUrl;
    private CatalogServicesServer cswServer;
    private GetRecordByIdRequest request;

    public GetRecordByIdRequester(String cswUrl) throws ServiceAccessException {
        this.cswUrl = cswUrl;
        System.out.println("GetRequesterById");
        try {

            cswServer = new CatalogServicesServer(new URL(cswUrl), SimpleCatalogHarvester.CSW_SERVICE_VERSION);
            request = cswServer.createGetRecordById();
        } catch (Exception e) {
            throw new ServiceAccessException(e);
        }
    }

    public MetadataRecord getRecordById(String metadataIdentifier) throws ServiceAccessException {
        request.setOutputSchema("http://www.isotc211.org/2005/gmd");
        request.setIds(metadataIdentifier);
        MetadataRecord record = null;
        try {
            System.out.println("URL -> " + request.getURL());
            InputStream is = request.getResponseStream();
            //System.out.println("Response:\n"+Xml.getString(Xml.loadStream(is)));
            MarshallerPool pool = CSWMarshallerPool.getInstance();
            Unmarshaller um = pool.acquireUnmarshaller();
            um.setSchema(null);
            GetRecordByIdResponseType response = (GetRecordByIdResponseType) um.unmarshal(is);
            if (!response.getAny().isEmpty()) {
                Metadata isoRecord = (Metadata) response.getAny().get(0);
                record = IsoMetadataRecordParser.parse(isoRecord);
                return record;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            //throw new ServiceAccessException(e);
        }
        if (record == null) {
            try {
                InputStream is = request.getResponseStream();
                //System.out.println("Response:\n"+Xml.getString(Xml.loadStream(is)));
                MarshallerPool pool = CSWMarshallerPool.getInstance();
                Unmarshaller um = pool.acquireUnmarshaller();
                um.setSchema(null);
                Element response = Xml.loadStream(is);
                record = IsoMetadataParser.parse(XmlParser.findElementByPath(response, "MD_Metadata"));
                return record;
            } catch (Exception e) {
                throw new ServiceAccessException(e);
            }

        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String url = "http://www.idee.es/csw-inspire-idee/srv/eng/csw";
            String id = "A.ES.SDGC.CP.MD.U.03091.xml";

            GetRecordByIdRequester requester = new GetRecordByIdRequester(url);
            MetadataRecord record = requester.getRecordById(id);
            record.print(System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
