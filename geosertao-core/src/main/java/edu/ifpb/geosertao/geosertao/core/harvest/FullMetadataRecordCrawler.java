/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.geosertao.geosertao.core.harvest;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;
/**
 *
 * @author Marcelo Augusto
 */
public class FullMetadataRecordCrawler {

    public static void crawl(MetadataRecord record) throws MalformedURLException, JAXBException, IOException, ServiceAccessException {
        int recordId = record.getId();
        if (record.getServices().isEmpty() && (record.getName() == null)) {
            MetadataRecord parsedRecord = null;
            try {
                if (record.getCatalog().getRecordByIdUrl().equals(record.getCatalog().getUrl())) {
                    GetRecordByIdRequester requester = new GetRecordByIdRequester(record.getCatalog().getRecordByIdUrl());
                    parsedRecord = requester.getRecordById(record.getMetadataIdentifier());
                } else {
                    parsedRecord = XmlDocumentRequester.getRecord(record.getCatalog().getRecordByIdUrl(), record.getMetadataIdentifier());
                }
                parsedRecord.setId(record.getId());
                parsedRecord.setMetadataIdentifier(record.getMetadataIdentifier());
                parsedRecord.setCatalog(record.getCatalog());
                MetadataRecordDaoIF dao = DaoFactoryBuilder.createDaoFactory().createMetadataRecordDao();
                parsedRecord.print(System.out);
                dao.update(parsedRecord);
            } catch (Exception e) {
                System.out.println("Error when processing " + record.getMetadataIdentifier());
                e.printStackTrace();
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        try {
            MetadataRecordDaoIF dao = DaoFactoryBuilder.createDaoFactory().createMetadataRecordDao();
            int id = 227503;
            int count = 227503;
            do {
                id++;
                System.out.println("Processing record " + id + " of " + count);
                MetadataRecord record = dao.find(id);
                if (record != null) {
                    FullMetadataRecordCrawler.crawl(record);
                }

            } while (id < count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
