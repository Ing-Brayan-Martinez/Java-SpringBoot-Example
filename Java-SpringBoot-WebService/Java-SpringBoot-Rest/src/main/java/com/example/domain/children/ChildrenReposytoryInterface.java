package com.example.domain.children;

import java.util.List;


public interface ChildrenReposytoryInterface {

    public void insertar(Children data);

    public void actualizar(Children data);

    public void eliminar(long key);

    public Children consultar(long key);

    public List<Children> consultarTodoList();
}
