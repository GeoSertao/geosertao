package edu.ifpb.geosertao.geosertao.core.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
public class ConceptSimilarity {
    
    @Id
    private long id;
    private double similarity;    
    @OneToOne
    private OntologyConcept searchConcept;
    @OneToOne
    private OntologyConcept databaseConcept;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public OntologyConcept getSearchConcept() {
        return searchConcept;
    }

    public void setSearchConcept(OntologyConcept searchConcept) {
        this.searchConcept = searchConcept;
    }

    public OntologyConcept getDatabaseConcept() {
        return databaseConcept;
    }

    public void setDatabaseConcept(OntologyConcept databaseConcept) {
        this.databaseConcept = databaseConcept;
    }
    
}
