package com.example.minimalrisk;

import java.util.ArrayList;

public class GsonTemplateCountryGraph {

    public ArrayList<GsonTemplateContinent> continents;
    public ArrayList<GsonTemplateBidirectionalLink> bidirectionalLinks;

    public GsonTemplateCountryGraph() {
        this.continents = new ArrayList<>(); 
        this.bidirectionalLinks = new ArrayList<>(); 
    }

}

