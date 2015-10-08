package edu.ifpb.geosertao.geosertao.core.entities;

import com.vividsolutions.jts.geom.Geometry;
import edu.ifpb.geosertao.geosertao.core.converters.LocalDatePersistenceConverter;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
public class MetadataRecord {

    @Id
    private long id;
    @OneToOne
    private CatalogService catalogService;
    @Column(length = 1000)
    private String name;
    @Column(length = 300)
    private String url;
    @Column(length = 500)
    private String keywords;
    @Column(length = 100, unique = true)
    private String metadataIdentifier;
    @Column(length = 300)
    private String publisher;
    @Column(length = 50)
    private String temporalExtent;
    @Temporal(TemporalType.DATE)
    @Basic
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate creationDate, publicationDate, revisionDate;
    @Type(type="org.hibernate.spatial.GeometryType")
    private Geometry coveredArea;
    @Column(length = 10000)
    private String lineage;
    @Column(length = 5000)
    private String textDescription;
    @Column(length = 30)
    private String language;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMetadataIdentifier() {
        return metadataIdentifier;
    }

    public void setMetadataIdentifier(String metadataIdentifier) {
        this.metadataIdentifier = metadataIdentifier;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTemporalExtent() {
        return temporalExtent;
    }

    public void setTemporalExtent(String temporalExtent) {
        this.temporalExtent = temporalExtent;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDate revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Geometry getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(Geometry coveredArea) {
        this.coveredArea = coveredArea;
    }

    public String getLineage() {
        return lineage;
    }

    public void setLineage(String lineage) {
        this.lineage = lineage;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
}
