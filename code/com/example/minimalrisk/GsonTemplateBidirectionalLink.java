package com.example.minimalrisk;

/**
 * Template class for converting data from Java objects to JSON and back, using the <a href="https://github.com/google/gson">Gson</a> library.
 */
public class GsonTemplateBidirectionalLink {

    public String fromCountry;
    public String toCountry;

    public GsonTemplateBidirectionalLink(String fromCountry, String toCountry) {
        this.fromCountry = fromCountry;
        this.toCountry = toCountry;
    }

}

