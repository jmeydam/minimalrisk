package com.example.minimalrisk;

import java.util.ArrayList;

public class GsonTemplateContinent {

    public String name;
    public ArrayList<GsonTemplateCountry> countries;

    public GsonTemplateContinent(String name, ArrayList<GsonTemplateCountry> countries) {
        this.name = name;
        this.countries = countries;
    }

}

