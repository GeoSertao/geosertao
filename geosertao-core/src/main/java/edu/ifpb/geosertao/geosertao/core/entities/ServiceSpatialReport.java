package edu.ifpb.geosertao.geosertao.core.entities;

import com.vividsolutions.jts.geom.Geometry;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Type;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
public class ServiceSpatialReport {

    @Id
    private long id;
    @OneToOne
    private Service service;
    private int frequency;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry coveredArea;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Geometry getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(Geometry coveredArea) {
        this.coveredArea = coveredArea;
    }

}
