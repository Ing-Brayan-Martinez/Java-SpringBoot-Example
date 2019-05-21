package com.example.graphql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class SchemaLoader {

    private final SchemaParser typeRegistry;
    private volatile String sdl = "";
    private volatile URL url;

    public SchemaLoader() {
        this.typeRegistry = new SchemaParser();
    }

    public TypeDefinitionRegistry onLoad() {

        try {
            url = Resources.getResource("schema.graphqls");
            sdl = Resources.toString(url, Charsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();

        }

        return typeRegistry.parse(sdl);
    }
}
