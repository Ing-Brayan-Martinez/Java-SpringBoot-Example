package com.example;

import com.example.service.dto.EmailDTO;
import com.example.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void exec() {

        EmailDTO msg = new EmailDTO("hola", "brayanmartinez827@gmail.com", "Este es el sugeto Conexion seguridad");

        EmailServiceImpl x = new EmailServiceImpl();
        x.sendMessage(msg);
    }
}
