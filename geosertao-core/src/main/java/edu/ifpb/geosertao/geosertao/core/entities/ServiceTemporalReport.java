package edu.ifpb.geosertao.geosertao.core.entities;

import edu.ifpb.geosertao.geosertao.core.converters.LocalDateTimePersistenceConverter;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
public class ServiceTemporalReport {

    @Id
    private long id;
    @OneToOne
    private Service service;
    private int frequency;
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime beginTime, endTime;

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

}
