package com.example.infraestructure.mail;

public class Prueba {

    public static void main(String[] args) {

        Correo msg = new Correo("hola", "brayanmartinez827@gmail.com", "Este es el sugeto Conexion seguridad");

        EmailManager x = new EmailManager();
        x.sendMessage(msg);
    }
}
