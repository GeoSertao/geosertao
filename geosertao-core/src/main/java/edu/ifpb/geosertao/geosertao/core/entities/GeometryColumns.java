package edu.ifpb.geosertao.geosertao.core.entities;

import edu.ifpb.geosertao.geosertao.core.entities.pkclasses.GeometryColumnsPK;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Entity
@Table (name = "geometry_columns")
@IdClass(GeometryColumnsPK.class)
public class GeometryColumns {

    @Id
    @Column (length = 256, name = "f_table_catalog")
    private String fTableCatalog; 
    @Id
    @Column (length = 256, name = "f_table_schema")
    private String fTableSchema;
    @Id
    @Column (length = 256, name = "f_table_name")
    private String fTableName;
    @Id
    @Column (length = 256, name = "f_geometry_column")
    private String fTableColumn;
    @Column (name = "coord_dimension", nullable = false)
    private int coordDimension;
    @Column (nullable = false)
    private int srid;
    @Column (length = 30, nullable = false)
    private String type;

    public String getfTableCatalog() {
        return fTableCatalog;
    }

    public void setfTableCatalog(String fTableCatalog) {
        this.fTableCatalog = fTableCatalog;
    }

    public String getfTableSchema() {
        return fTableSchema;
    }

    public void setfTableSchema(String fTableSchema) {
        this.fTableSchema = fTableSchema;
    }

    public String getfTableName() {
        return fTableName;
    }

    public void setfTableName(String fTableName) {
        this.fTableName = fTableName;
    }

    public String getfTableColumn() {
        return fTableColumn;
    }

    public void setfTableColumn(String fTableColumn) {
        this.fTableColumn = fTableColumn;
    }

    public int getCoordDimension() {
        return coordDimension;
    }

    public void setCoordDimension(int coordDimension) {
        this.coordDimension = coordDimension;
    }

    public int getSrid() {
        return srid;
    }

    public void setSrid(int srid) {
        this.srid = srid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
