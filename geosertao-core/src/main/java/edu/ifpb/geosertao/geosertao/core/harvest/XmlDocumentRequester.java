
package edu.ifpb.geosertao.geosertao.core.harvest;

import edu.ifpb.geosertao.geosertao.core.converters.XmlParser;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Iterator;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.fao.geonet.csw.common.util.Xml;
import org.geotoolkit.csw.xml.CSWMarshallerPool;
import org.geotoolkit.csw.xml.v202.GetRecordByIdResponseType;
import org.geotoolkit.metadata.iso.DefaultMetadata;
import org.geotoolkit.xml.MarshallerPool;
import org.jdom.Element;
import org.opengis.metadata.Metadata;

/**
 *
 * @author Fabio
 */
public class XmlDocumentRequester {

    public static MetadataRecord getRecord(String serviceUrl, String metadataId) throws ServiceAccessException {

        try {
            String url = serviceUrl + metadataId;
            System.out.println("URL -> " + url);
            // String url = serviceUrl+metadataId;
            GetMethod method = new GetMethod(url);
            HttpClient client = new HttpClient();
            client.executeMethod(method);
            //Element response = Xml.loadStream(method.getResponseBodyAsStream());

            InputStream is = method.getResponseBodyAsStream();

            System.out.println("Is ->" + is);

            MarshallerPool pool = CSWMarshallerPool.getInstance();
            Unmarshaller um = pool.acquireUnmarshaller();
            DefaultMetadata isoRecord = (DefaultMetadata) um.unmarshal(is);
            MetadataRecord record = IsoMetadataRecordParser.parse(isoRecord);

            return record;
            //return IsoMetadataParser.parse(response);
        } catch (Exception e) {
        }

        try {
            String url = serviceUrl + metadataId;
            System.out.println("URL -> " + url);
            // String url = serviceUrl+metadataId;
            GetMethod method = new GetMethod(url);
            HttpClient client = new HttpClient();
            client.executeMethod(method);
            //Element response = Xml.loadStream(method.getResponseBodyAsStream());

            InputStream is = method.getResponseBodyAsStream();
            Element response = Xml.loadStream(is);
            MetadataRecord record = IsoMetadataParser.parse(response);
            return record;
        } catch (Exception e) {
            throw new ServiceAccessException(e);
        }

    }

    public static void main(String[] args) {
        try {
            String url = "http://geodiscover.cgdi.ca/wes/CSWSearchClient/OWSResults/DisplayFullMetadataXML.jsp?previewFormat=iso19139&uuid=";
            String id = "7921A67B-0142-998D-F992-779E354C5F51";
            MetadataRecord record = XmlDocumentRequester.getRecord(url, id);

            record.print(System.out);
            System.out.println("Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
