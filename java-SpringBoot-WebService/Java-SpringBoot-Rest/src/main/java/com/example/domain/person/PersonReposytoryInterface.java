package com.example.domain.person;

import java.util.List;

public interface PersonReposytoryInterface {

    public void insert(Person data);

    public void update(Person data);

    public void delete(int key);

    public Person select(int key);

    public List<Person> selectAll();
}
