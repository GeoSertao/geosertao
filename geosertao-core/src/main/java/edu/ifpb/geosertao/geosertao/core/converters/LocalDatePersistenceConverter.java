package edu.ifpb.geosertao.geosertao.core.converters;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Converter
public class LocalDatePersistenceConverter 
        implements AttributeConverter<LocalDate, Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute != null){
            return Date.valueOf(attribute);
        }else 
            return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date attribute) {
        if (attribute != null){
            return attribute.toLocalDate();           
        }else
            return null;
    }

}
