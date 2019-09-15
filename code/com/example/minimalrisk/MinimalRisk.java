package com.example.minimalrisk;

/**
 * TODO add comment
 */
public class MinimalRisk {

    private static String countryListExampleJson = 
        "{\n"
      + "  \"countries\": [\n"
      + "    {\"name\": \"11\", \"player\": \"A\", \"count\": 1, \"modified\": false },\n"
      + "    {\"name\": \"12\", \"player\": \"A\", \"count\": 1, \"modified\": false },\n"
      + "    {\"name\": \"13\", \"player\": \"A\", \"count\": 1, \"modified\": false }\n"
      + "  ]\n"
      + "}\n";

    // initialization

    /**
     * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
     */
    public static String initialAllocationOfCountries(String countryGraphJSON, String playerA, String playerB) {
        return countryGraphJSON;
    }

    /**
     * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
     */
    public static String initialAllocationOfExtraTroops(String countryGraphJSON, int extraTroopsEach) {
        return countryGraphJSON;
    }


    // automatic move by game engine:
    //
    // 1. allocationOfExtraTroops
    // 2. attack
    // 3. moveTroops

    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String allocationOfExtraTroops(String countryGraphJSON, String player, int extraTroops) {
        return countryGraphJSON;
    }

    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String attack(String countryGraphJSON, String attackingPlayer) {
        return countryGraphJSON;
    }

    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String moveTroops(String countryGraphJSON, String player) {
        return countryGraphJSON;
    }
    
    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String moveTroops(String countryGraphJSON, String player, int troops) {
        return countryGraphJSON;
    }

    /**
    * @return true if one player has captured a whole continent
    */
    public static boolean gameOver(String countryGraphJSON) {
        return false;
    }


    // helper methods for implementation of attack method; can also be used to guide player / to valdidate player moves

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleTargetCountries(String countryGraphJSON, String attackingPlayer) {
        return countryListExampleJson;
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleTargetCountries(String countryGraphJSON, String attackingPlayer, String attackingCountry) {
        return countryListExampleJson;
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleAttackingCountries(String countryGraphJSON, String attackingPlayer, String targetCountry) {
        return countryListExampleJson;
    }

    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String battle(String countryGraphJSON, String attackingCountry, String targetCountry) {
        return countryGraphJSON;
    }


    // helper methods for implementation of moveTroops method; can also be used to guide player / to valdidate player moves

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleDestinations(String countryGraphJSON, String player) {
        return countryListExampleJson;
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleDestinations(String countryGraphJSON, String player, String sourceCountry) {
        return countryListExampleJson;
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleSources(String countryGraphJSON, String player) {
        return countryListExampleJson;
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleSources(String countryGraphJSON, String player, String destinationCountry) {
        return countryListExampleJson;
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String shortestPath(String countryGraphJSON, String player, String sourceCountry, String destinationCountry) {
        return countryListExampleJson;
    }

}

