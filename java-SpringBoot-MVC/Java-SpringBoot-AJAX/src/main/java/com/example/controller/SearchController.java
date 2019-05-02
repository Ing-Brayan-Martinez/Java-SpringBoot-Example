package com.example.controller;

import com.example.model.AjaxResponseBody;
import com.example.model.SearchCriteria;
import com.example.model.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SearchController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {

        final AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        //Obtener los usuarios.
        final List<User> users = userService.findByUserNameOrEmail(search.getUsername());

        //Saber si existe un usuario.
        if (users.isEmpty()) {
            result.setMsg("no user found!");

        } else {
            result.setMsg("success");

        }

        //Establecer un mensaje
        result.setResult(users);

        //Enviar respuesta.
        return ResponseEntity.ok(result);

    }

}
