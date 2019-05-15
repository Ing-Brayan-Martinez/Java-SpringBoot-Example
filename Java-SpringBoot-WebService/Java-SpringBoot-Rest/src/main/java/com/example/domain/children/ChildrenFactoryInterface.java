package com.example.domain.children;

import com.example.infraestructure.otros.ChildrenCommand;


public interface ChildrenFactoryInterface {

    public Children toChildren(ChildrenCommand command);

}
