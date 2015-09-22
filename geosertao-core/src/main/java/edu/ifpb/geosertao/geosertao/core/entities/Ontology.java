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
public class Ontology {

    @Id
    private long id;
    @Column(length = 50)
    private String domain;
    @Column(length = 300)
    private String uri;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
}
