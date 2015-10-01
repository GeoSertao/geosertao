/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ifpb.geosertao.geosertao.core.harvest;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Iterator;
import br.geosertao.crawler.url.UrlClassifier;
import br.geosertao.entities.MetadataRecord;
import br.geosertao.entities.MetadataRecordUrl;

/**
 *
 * @author Fabio
 */
public class OgcServiceCrawler {

       public static void crawl(MetadataRecord record){
             Collection<MetadataRecordUrl> urlsCollection = record.getUrls();
             if(! urlsCollection.isEmpty()){
                   Iterator<MetadataRecordUrl> urls = urlsCollection.iterator();
                   while(urls.hasNext()){
                        MetadataRecordUrl currentUrl = urls.next();
                        try{
                             
                             int resourceType = UrlClassifier.classify(currentUrl.getUrl());
                        }
                        catch(MalformedURLException e){}
                   }
             }

       }

}
