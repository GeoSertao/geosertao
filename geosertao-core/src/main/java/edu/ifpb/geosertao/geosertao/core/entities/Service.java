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
public class Service {

    @Id
    private long id;
    @OneToOne
    private MetadataRecord metadataRecord;
    @Column(length = 200)
    private String name;
    @Column(length = 3000)
    private String textDescription;
    @Column(length = 500)
    private String keywords;
    @Column(length = 300, unique = true)
    private String url;
    @Column(length = 100)
    private String metadataIndentifier;
    @Column(length = 200)
    private String publisher;
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime beginTime, endTime;    
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Geometry coveredArea;
    @Column(length = 50)
    private String updateFrequency;    
    private int type;
    @Column(length = 100)
    private String dType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MetadataRecord getMetadataRecord() {
        return metadataRecord;
    }

    public void setMetadataRecord(MetadataRecord metadataRecord) {
        this.metadataRecord = metadataRecord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMetadataIndentifier() {
        return metadataIndentifier;
    }

    public void setMetadataIndentifier(String metadataIndentifier) {
        this.metadataIndentifier = metadataIndentifier;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getdType() {
        return dType;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }
    
}
