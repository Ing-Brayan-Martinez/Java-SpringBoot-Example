package com.example.domain.children;

import java.util.List;


public interface ChildrenReposytoryInterface {

    public void insertar(Children data);

    public void actualizar(Children data);

    public void eliminar(int key);

    public Children consultar(int key);

    public List<Children> consultarTodoList();
}
