package com.example.convert.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public final class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean entity) {
        return entity != null && entity ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String table) {
        return table.equals("Y");
    }

}
