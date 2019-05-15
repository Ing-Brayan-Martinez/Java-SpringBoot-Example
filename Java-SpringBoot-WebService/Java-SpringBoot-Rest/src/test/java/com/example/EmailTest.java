package com.example;

import com.example.infraestructure.mail.Correo;
import com.example.infraestructure.mail.EmailManager;
import org.junit.Test;

public class EmailTest {

    @Test
    public void exec() {

        Correo msg = new Correo("hola", "brayanmartinez827@gmail.com", "Este es el sugeto Conexion seguridad");

        EmailManager x = new EmailManager();
        x.sendMessage(msg);
    }
}
