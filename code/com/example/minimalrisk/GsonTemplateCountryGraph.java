package com.example.minimalrisk;

import java.util.ArrayList;
import com.google.gson.Gson;

public class GsonTemplateCountryGraph {

    public ArrayList<GsonTemplateContinent> continents;
    public ArrayList<GsonTemplateBidirectionalLink> bidirectionalLinks;

    public GsonTemplateCountryGraph() {

        // continent 1
        ArrayList<GsonTemplateCountry> countries1 = new ArrayList<>();
        for (int i = 11; i < 20; i++) {
            countries1.add(new GsonTemplateCountry(String.valueOf(i), "A", 1, false));
        }
        GsonTemplateContinent continent1 = new GsonTemplateContinent("1", countries1);

        // continent 2
        ArrayList<GsonTemplateCountry> countries2 = new ArrayList<>();
        for (int i = 21; i < 30; i++) {
            countries2.add(new GsonTemplateCountry(String.valueOf(i), "B", 1, false));
        }
        GsonTemplateContinent continent2 = new GsonTemplateContinent("2", countries2);

        this.continents = new ArrayList<>();
        this.continents.add(continent1);
        this.continents.add(continent2);

        this.bidirectionalLinks = new ArrayList<>();
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("11", "12"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("12", "13"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("14", "15"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("15", "16"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("17", "18"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("18", "19"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("11", "14"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("14", "17"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("12", "15"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("15", "18"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("13", "16"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("16", "19"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("14", "18"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("11", "15"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("15", "19"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("12", "16"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("12", "14"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("13", "15"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("15", "17"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("16", "18"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("21", "22"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("22", "23"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("24", "25"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("25", "26"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("27", "28"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("28", "29"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("21", "24"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("24", "27"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("22", "25"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("25", "28"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("23", "26"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("26", "29"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("24", "28"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("21", "25"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("25", "29"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("22", "26"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("22", "24"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("23", "25"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("25", "27"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("26", "28"));
        this.bidirectionalLinks.add(new GsonTemplateBidirectionalLink("16", "24"));

    }

}

