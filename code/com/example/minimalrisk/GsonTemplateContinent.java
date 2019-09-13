package com.example.minimalrisk;

import java.util.ArrayList;

class GsonTemplateContinent {

    String name;
    ArrayList<GsonTemplateCountry> countries;

    GsonTemplateContinent(String name, ArrayList<GsonTemplateCountry> countries) {
        this.name = name;
        this.countries = countries;
    }

}

