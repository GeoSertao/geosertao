package edu.ifpb.geosertao.geosertao.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */

@Entity
public class ThematicTag {

    @Id
    @Column(nullable = false)
    private long id;
    @ManyToOne
    private FeatureType featuretype;
    @ManyToOne
    private OntologyConcept concept;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FeatureType getFeaturetype() {
        return featuretype;
    }

    public void setFeaturetype(FeatureType featuretype) {
        this.featuretype = featuretype;
    }

    public OntologyConcept getConcept() {
        return concept;
    }

    public void setConcept(OntologyConcept concept) {
        this.concept = concept;
    }
    
}
