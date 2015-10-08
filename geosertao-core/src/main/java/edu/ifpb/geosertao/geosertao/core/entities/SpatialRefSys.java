package edu.ifpb.geosertao.geosertao.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
@Table(name = "spatial_ref_sys")
public class SpatialRefSys {

    @Id
    @Column(nullable = false)
    private int srid;
    @Column(name = "auth_name", length = 256)
    private String authName;
    @Column (name="auth_srid")
    private int authSrid;
    @Column(length = 2048)
    private String srtext, proj4text;

    public int getSrid() {
        return srid;
    }

    public void setSrid(int srid) {
        this.srid = srid;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public int getAuthSrid() {
        return authSrid;
    }

    public void setAuthSrid(int authSrid) {
        this.authSrid = authSrid;
    }

    public String getSrtext() {
        return srtext;
    }

    public void setSrtext(String srtext) {
        this.srtext = srtext;
    }

    public String getProj4text() {
        return proj4text;
    }

    public void setProj4text(String proj4text) {
        this.proj4text = proj4text;
    }

    
}
