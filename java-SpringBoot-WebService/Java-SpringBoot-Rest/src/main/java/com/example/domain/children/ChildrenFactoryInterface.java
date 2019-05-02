package com.example.domain.children;

import com.example.aplication.children.ChildrenCommand;


public interface ChildrenFactoryInterface {

    public Children toChildren(ChildrenCommand command);

}