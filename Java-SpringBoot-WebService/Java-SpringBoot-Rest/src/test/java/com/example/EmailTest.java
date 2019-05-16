package com.example;

import com.example.infraestructure.mail.EMail;
import com.example.infraestructure.mail.EmailManager;
import org.junit.Test;

public class EmailTest {

    @Test
    public void exec() {

        EMail msg = new EMail("hola", "brayanmartinez827@gmail.com", "Este es el sugeto Conexion seguridad");

        EmailManager x = new EmailManager();
        x.sendMessage(msg);
    }
}
