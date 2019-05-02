package com.ingbrayanmartinez.fabricabeans.main;

import com.ingbrayanmartinez.fabricabeans.curso.Curso;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;

public class Main implements Serializable {

    private static ApplicationContext contex;

    public static void main(String[] args) {
        contex = new ClassPathXmlApplicationContext("applicationContex.xml");

        Curso curso = (Curso) contex.getBean("curso");
        System.out.println(curso.getId());
    }
}
