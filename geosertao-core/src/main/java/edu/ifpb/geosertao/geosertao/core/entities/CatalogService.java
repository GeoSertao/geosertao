package edu.ifpb.geosertao.geosertao.core.entities;

import edu.ifpb.geosertao.geosertao.core.converters.LocalDateTimePersistenceConverter;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
public class CatalogService {

    @Id
    private long id;
    @Column(length = 200)
    private String provider;
    @Column(length = 300, unique = true)
    private String url;
    @Column (name = "textdescription", length = 3000)
    private String description;
    @Column (length = 300, name = "recordbyidurl")
    private String recordByIdUrl;
    @Column(name = "lastcrawling", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = true)
    @Convert(converter = LocalDateTimePersistenceConverter.class)    
    private LocalDateTime lastCrawling; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecordByIdUrl() {
        return recordByIdUrl;
    }

    public void setRecordByIdUrl(String recordByIdUrl) {
        this.recordByIdUrl = recordByIdUrl;
    }

    public LocalDateTime getLastCrawling() {
        return lastCrawling;
    }

    public void setLastCrawling(LocalDateTime lastCrawling) {
        this.lastCrawling = lastCrawling;
    }
    
}
