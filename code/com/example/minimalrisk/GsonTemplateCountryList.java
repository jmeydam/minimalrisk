package com.example.minimalrisk;

import java.util.ArrayList;
import com.google.gson.Gson;

public class GsonTemplateCountryList {

    public ArrayList<GsonTemplateCountry> countries;

    public GsonTemplateCountryList() {
        this.countries = new ArrayList<>();
        for (int i = 11; i < 14; i++) {
            countries.add(new GsonTemplateCountry(String.valueOf(i), "A", 1, false));
        }
    }

}

