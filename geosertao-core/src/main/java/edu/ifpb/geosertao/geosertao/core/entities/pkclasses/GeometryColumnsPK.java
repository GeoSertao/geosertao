package edu.ifpb.geosertao.geosertao.core.entities.pkclasses;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
public class GeometryColumnsPK implements Serializable{
    
    private String fTableCatalog, fTableSchema, fTableName, fTableColumn;

    public GeometryColumnsPK() {
    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.fTableCatalog);
        hash = 41 * hash + Objects.hashCode(this.fTableSchema);
        hash = 41 * hash + Objects.hashCode(this.fTableName);
        hash = 41 * hash + Objects.hashCode(this.fTableColumn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GeometryColumnsPK other = (GeometryColumnsPK) obj;
        if (!Objects.equals(this.fTableCatalog, other.fTableCatalog)) {
            return false;
        }
        if (!Objects.equals(this.fTableSchema, other.fTableSchema)) {
            return false;
        }
        if (!Objects.equals(this.fTableName, other.fTableName)) {
            return false;
        }
        if (!Objects.equals(this.fTableColumn, other.fTableColumn)) {
            return false;
        }
        return true;
    }        
    
}
