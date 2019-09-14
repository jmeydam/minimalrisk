package com.example.minimalrisk;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

class GsonTest {

    /*
    jenss-macbook-air:minimalrisk jensmeydam$ export CLASSPATH=.:gson-2.8.5.jar
    jenss-macbook-air:minimalrisk jensmeydam$ echo $CLASSPATH
    .:gson-2.8.5.jar
    jenss-macbook-air:minimalrisk jensmeydam$ javac com/example/minimalrisk/GsonTest.java
    jenss-macbook-air:minimalrisk jensmeydam$ java com/example/minimalrisk/GsonTest
    OK!
    jenss-macbook-air:minimalrisk jensmeydam$ 
    */

    public static void main(String[] args) throws IOException {
        System.out.println("Gson with template object:");
        // country_graph_init.json
        Gson gson = new Gson();
        String json = gson.toJson(new GsonTemplate());
        System.out.println(json);

        System.out.println("Gson with json from file:");
        // Graph g = Graph();
        Path path = FileSystems.getDefault().getPath("country_graph_init.json");
        String countryGraphString = Files.readString(path, StandardCharsets.UTF_8);
        Gson gson2 = new Gson();
        GsonTemplate countryGraphObject = gson2.fromJson(countryGraphString, GsonTemplate.class);
        String json2 = gson2.toJson(countryGraphObject);
        System.out.println(json2);
    }

}
