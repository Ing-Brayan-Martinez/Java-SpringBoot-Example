package com.example;

import com.example.graphql.DataFetcherLoader;
import com.example.graphql.SchemaLoader;
import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MainApp {

    @Autowired
    private SchemaLoader typeLoader;

    @Autowired
    private DataFetcherLoader dataLoader;

    @Bean
    public GraphQLSchema schema() {
        final SchemaGenerator schemaGenerator = new SchemaGenerator();
        final GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeLoader.onLoad(), dataLoader.onLoad());
        return schema;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

//    @Bean
//    public GraphQLSchema schema() {
//        return GraphQLSchema.newSchema()
//                .query(GraphQLObjectType.newObject()
//                        .name("mitest")
//                        .field(field -> field
//                                .name("test")
//                                .type(Scalars.GraphQLString)
//                                .dataFetcher(environment -> "response")
//                        )
//                        .field(field -> field
//                                .name("me")
//                                .type(Scalars.GraphQLString)
//                                .dataFetcher(environment -> "brayan martinez")
//                        )
//                        .build())
//                .build();
//    }

}
