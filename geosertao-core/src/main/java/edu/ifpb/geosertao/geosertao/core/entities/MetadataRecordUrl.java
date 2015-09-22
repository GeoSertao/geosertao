package edu.ifpb.geosertao.geosertao.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
public class MetadataRecordUrl {
    
    @Id
    private long id;
    private int provider;
    @Column(length = 300)
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}
