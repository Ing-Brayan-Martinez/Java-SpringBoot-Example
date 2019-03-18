package com.example.aplication.person;

public class PersonQuery {

    public static PersonQuery empty() {
        return new PersonQuery();
    }

    private String id;

    public PersonQuery() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
