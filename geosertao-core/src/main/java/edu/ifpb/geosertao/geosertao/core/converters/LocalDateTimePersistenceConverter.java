package edu.ifpb.geosertao.geosertao.core.converters;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author douglasgabriel
 * @version 0.1
 */
@Converter
public class LocalDateTimePersistenceConverter
        implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        if (attribute != null)
            return Timestamp.valueOf(attribute);
        else return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData != null)
            return dbData.toLocalDateTime();
        else return null;
    }

}
