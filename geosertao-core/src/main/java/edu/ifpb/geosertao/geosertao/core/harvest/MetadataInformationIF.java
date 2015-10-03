package edu.ifpb.geosertao.geosertao.core.harvest;

import java.util.Collection;
import java.util.Date;
import br.geosertao.entities.BoundingBoxIF;

/**
 *
 * @author Fabio
 */
public interface MetadataInformationIF {

    public String getTitle();

    public void setTitle(String title);

    public String getPublisher();

    public void setPublisher(String publisher);

    public String getTextDescription();

    public void setTextDescription(String textDescription);

    public String getMetadataIdentifier();

    public void setMetadataIdentifier(String metadataIdentifier);

    public Collection<String> getKeywords();

    public void setKeywords(Collection<String> keywords);

    public String getTemporalExtent();

    public void setTemporalExtent(String temporalExt);

    public Date getPublicationDate();

    public void setPublicationDate(Date publicationDate);

    public Date getCreationDate();

    public void setCreationDate(Date creationDate);

    public Date getRevisionDate();

    public void setRevisionDate(Date revisionDate);

    public String getUpdateFrequency();

    public void setUpdateFrequency(String updateFrequency);

    public BoundingBoxIF getCoveredArea();

    public void setCoveredArea(BoundingBoxIF coveredArea);

    public Collection<String> getLinks();

    public void setLinks(Collection<String> links);

}
