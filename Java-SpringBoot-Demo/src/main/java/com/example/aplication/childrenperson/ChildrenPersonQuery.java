package com.example.aplication.childrenperson;

public class ChildrenPersonQuery {

    public static ChildrenPersonQuery empty() {
        return new ChildrenPersonQuery();
    }

    private String id;

    public ChildrenPersonQuery() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
