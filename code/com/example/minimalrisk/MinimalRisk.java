package com.example.minimalrisk;

import java.util.ArrayList;

public class MinimalRisk {

    /**
     * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
     */
    public static String initialAllocationOfCountries(String countryGraphJSON, String playerA, String playerB) {
        Board board = new Board(countryGraphJSON);
        board.initialAllocationOfCountries(playerA, playerB);
        return board.getCountryGraphJSON();
    }

    /**
     * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
     */
    public static String initialAllocationOfExtraTroops(String countryGraphJSON, int extraTroopsEach) {
        Board board = new Board(countryGraphJSON);
        board.initialAllocationOfExtraTroops(6);
        return board.getCountryGraphJSON();
    }

    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String allocationOfExtraTroops(String countryGraphJSON, String player, int extraTroops) {
        Board board = new Board(countryGraphJSON);
        board.resetModified();
        board.allocationOfExtraTroops(player, 2);
        return board.getCountryGraphJSON();
    }

    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String attack(String countryGraphJSON, String attackingPlayer) {
        Board board = new Board(countryGraphJSON);
        board.resetModified();
        board.attack(attackingPlayer);
        return board.getCountryGraphJSON();
    }

    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String moveTroops(String countryGraphJSON, String player) {
        Board board = new Board(countryGraphJSON);
        board.resetModified();
        board.moveTroops(player);
        return board.getCountryGraphJSON();
    }
    
    /**
    * @return true if one player has captured a whole continent
    */
    public static boolean gameOver(String countryGraphJSON) {
        Board board = new Board(countryGraphJSON);
        return board.gameOver();
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleTargetCountries(String countryGraphJSON, String attackingPlayer) {
        Board board = new Board(countryGraphJSON);
        ArrayList<Node> possibleTargetCountries = board.possibleTargetCountries(attackingPlayer);
        return Board.getCountryListJSON(possibleTargetCountries);
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleTargetCountries(String countryGraphJSON, String attackingPlayer, String attackingCountry) {
        Board board = new Board(countryGraphJSON);
        // TODO
        ArrayList<Node> possibleTargetCountries = board.possibleTargetCountries(attackingPlayer);
        return Board.getCountryListJSON(possibleTargetCountries);
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleAttackingCountries(String countryGraphJSON, String attackingPlayer, String targetCountry) {
        Board board = new Board(countryGraphJSON);
        ArrayList<Node> possibleAttackingCountries = board.possibleAttackingCountries(attackingPlayer, board.getNode(targetCountry));
        return Board.getCountryListJSON(possibleAttackingCountries);
    }

    /**
    * @return String with JSON representation of country graph (same structure as parameter countryGraphJSON)
    */
    public static String battle(String countryGraphJSON, String attackingCountry, String targetCountry) {
        Board board = new Board(countryGraphJSON);
        board.resetModified();
        board.battle(board.getNode(attackingCountry), board.getNode(targetCountry));
        return board.getCountryGraphJSON();
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleDestinations(String countryGraphJSON, String player) {
        Board board = new Board(countryGraphJSON);
        ArrayList<Node> possibleDestinations = board.possibleDestinations(player);
        return Board.getCountryListJSON(possibleDestinations);
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleDestinations(String countryGraphJSON, String player, String sourceCountry) {
        Board board = new Board(countryGraphJSON);
        // TODO
        ArrayList<Node> possibleDestinations = board.possibleDestinations(player);
        return Board.getCountryListJSON(possibleDestinations);
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleSources(String countryGraphJSON, String player) {
        Board board = new Board(countryGraphJSON);
        ArrayList<Node> possibleSources = board.possibleSources(player);
        return Board.getCountryListJSON(possibleSources);
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String possibleSources(String countryGraphJSON, String player, String destinationCountry) {
        Board board = new Board(countryGraphJSON);
        // TODO
        ArrayList<Node> possibleSources = board.possibleSources(player);
        return Board.getCountryListJSON(possibleSources);
    }

    /**
    * @return String with JSON representation of list of countries
    */
    public static String shortestPath(String countryGraphJSON, String player, String sourceCountry, String destinationCountry) {
        Board board = new Board(countryGraphJSON);
        ArrayList<Node> shortestPath = board.shortestPath(sourceCountry, destinationCountry, player);
        return Board.getCountryListJSON(shortestPath);
    }

}

