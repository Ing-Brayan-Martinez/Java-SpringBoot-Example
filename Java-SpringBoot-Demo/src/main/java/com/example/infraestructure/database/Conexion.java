package com.example.infraestructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Atributos constantes.
    private final String driver;
    private final String user;
    private final String pass;
    private final String url;

    private static Conexion instance;

    //Atributos.
    private Connection cnn;




    //Constructor privado para hacer una y solo una instancia.
    public Conexion() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url    = "jdbc:mysql://localhost:3306/Prueba";
        this.user   = "root";
        this.pass   = "12345678";
        this.cnn    = null;

        try {
            //Importacion del driver.
            Class.forName(this.driver);

            //Conexion Conexion la base de datos.
            this.cnn = DriverManager.getConnection(this.url, this.user, this.pass);

        } catch(SQLException ex) {
            System.out.println("Error de mysql Se ha encontrado un error que es: "+ex.getMessage());

        } catch (ClassNotFoundException e) {


        }

    }


    public synchronized static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }


    public Connection getConnection() {
        return this.cnn;
    }


    public void close() {
        instance = null;
    }
}
