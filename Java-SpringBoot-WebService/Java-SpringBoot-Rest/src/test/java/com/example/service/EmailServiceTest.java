/*
 *  Copyright (c) 2020. brayan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.example.service;

import com.example.service.dto.EmailDTO;
import com.example.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;

public class EmailServiceTest {

    @Test
    public void exec() {

        EmailDTO msg = new EmailDTO("hola", "brayanmartinez827@gmail.com", "Este es el sugeto Conexion seguridad");

        EmailServiceImpl x = new EmailServiceImpl();
        x.sendMessage(msg);
    }
}
