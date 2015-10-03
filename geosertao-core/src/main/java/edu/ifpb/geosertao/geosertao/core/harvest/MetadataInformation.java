package edu.ifpb.geosertao.geosertao.core.harvest;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import br.geosertao.entities.BoundingBoxIF;

/**
 *
 * @author Fabio
 */
public class MetadataInformation implements MetadataInformationIF {

    private String title;

    private String publisher;

    private String textDescription;

    private String metadataIdentifier;

    private Collection<String> keywords;

    private Collection<String> links;

    private BoundingBoxIF coveredArea;

    private String temporalExtent;

    private Date creationDate;

    private Date publicationDate;

    private Date revisionDate;

    private String updateFrequency;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getMetadataIdentifier() {
        return metadataIdentifier;
    }

    public void setMetadataIdentifier(String metadataIdentifier) {
        this.metadataIdentifier = metadataIdentifier;
    }

    public Collection<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Collection<String> keywords) {
        this.keywords = keywords;
    }

    public Collection<String> getLinks() {
        return links;
    }

    public void setLinks(Collection<String> links) {
        this.links = links;
    }

    public BoundingBoxIF getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(BoundingBoxIF coveredArea) {
        this.coveredArea = coveredArea;
    }

    public String getTemporalExtent() {
        return temporalExtent;
    }

    public void setTemporalExtent(String temporalExtent) {
        this.temporalExtent = temporalExtent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public void print(PrintStream out) {
        out.println("Title: " + getTitle());
        out.println("Publisher: " + getPublisher());
        out.println("TextDescription: " + getTextDescription());
        out.println("MetadataIdentifier: " + getMetadataIdentifier());
        out.println("Keywords: " + getCollectionAsString(getKeywords()));
        out.println("Links: " + getCollectionAsString(getLinks()));
        out.println("Covered Area: " + getCoveredArea());
        out.println("Temporal Extent: " + getTemporalExtent());
        System.out.println("Creation Date: " + getCreationDate());
        out.println("Publication Date: " + getPublicationDate());
        out.println("Last Update Date: " + getRevisionDate());
        out.println("Update Frequency: " + getUpdateFrequency());

        out.println("");
    }

    private String getCollectionAsString(Collection col) {
        String result = "";
        if (col != null) {
            Iterator it = col.iterator();
            while (it.hasNext()) {
                result += it.next().toString();
                if (it.hasNext()) {
                    result += ", ";
                }
            }
        }
        return result;
    }

}
