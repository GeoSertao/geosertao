package edu.ifpb.geosertao.geosertao.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
public class MetadataRecordUrl {
    
    @Id
    private long id;
    @OneToOne
    private MetadataRecord provider;
    @Column(length = 300)
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MetadataRecord getProvider() {
        return provider;
    }

    public void setProvider(MetadataRecord provider) {
        this.provider = provider;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}
