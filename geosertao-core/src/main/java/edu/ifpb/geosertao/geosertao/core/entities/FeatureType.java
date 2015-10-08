package edu.ifpb.geosertao.geosertao.core.entities;

import com.vividsolutions.jts.geom.Geometry;
import edu.ifpb.geosertao.geosertao.core.converters.LocalDateTimePersistenceConverter;
import java.time.LocalDateTime;
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
public class FeatureType {

    @Id
    private long id;
    @Column(length = 200)
    private String name;
    @Column(length = 300)
    private String title;
    @Column(length = 3000)
    private String textDescription;
    @OneToOne
    private Service service;
    @Column(length = 20)
    private String format;
    @Column(length = 100)
    private String metadataIdentifier;
    @Column(length = 200)
    private String keywords;
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime beginTime, endTime;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry coveredArea;
    @Column(length = 100)
    private String dType;
    @Column(length = 50)
    private String updateFrequency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMetadataIdentifier() {
        return metadataIdentifier;
    }

    public void setMetadataIdentifier(String metadataIdentifier) {
        this.metadataIdentifier = metadataIdentifier;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Geometry getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(Geometry coveredArea) {
        this.coveredArea = coveredArea;
    }

    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }
}
