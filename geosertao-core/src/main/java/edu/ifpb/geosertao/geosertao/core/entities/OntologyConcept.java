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
public class OntologyConcept {

    @Id
    private long id;
    @OneToOne
    private Ontology ontology;
    @Column(length = 300)
    private String uri;
    @Column(length = 50)
    private String name, nomalizedName;    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ontology getOntology() {
        return ontology;
    }

    public void setOntology(Ontology ontology) {
        this.ontology = ontology;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
