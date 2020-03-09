package com.example.util;



public final class BooleanConverter {

    public static String toDatabaseColumn(Boolean entity) {
        return entity != null && entity ? "Y" : "N";
    }

    public static Boolean toEntityAttribute(String table) {
        return table.equals("Y");
    }

}
