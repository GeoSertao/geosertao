package edu.ifpb.geosertao.geosertao.core.harvest;

import java.io.InputStream;
import java.net.URL;
import javax.xml.bind.Unmarshaller;
import org.fao.geonet.csw.common.util.Xml;
import org.geotoolkit.csw.CatalogServicesServer;
import org.geotoolkit.csw.GetRecordByIdRequest;
import org.geotoolkit.csw.xml.CSWMarshallerPool;
import org.geotoolkit.xml.MarshallerPool;

public class TestGetRecordById {

    public static void main(String[] args) {
        try {
            String url = "http://clearinghouse.cisc.gmu.edu/geonetwork/srv/en/csw";
            CatalogServicesServer cswServer = new CatalogServicesServer(new URL(url), SimpleCatalogHarvester.CSW_SERVICE_VERSION);
            MarshallerPool pool = CSWMarshallerPool.getInstance();
            Unmarshaller um = pool.acquireUnmarshaller();
            GetRecordByIdRequest request = cswServer.createGetRecordById();
            request.setOutputSchema("http://www.isotc211.org/2005/gmd");
            String[] ids = {"jrc-img2k_pr1_es19_multi"};
            request.setIds(ids);

            InputStream is = request.getResponseStream();
            System.out.println("Response:\n");
            System.out.println(Xml.getString(Xml.loadStream(is)));
                 // GetRecordByIdResponseType response = ((JAXBElement<GetRecordByIdResponseType>) um.unmarshal(is)).getValue();
            // System.out.println("Response:"+response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
