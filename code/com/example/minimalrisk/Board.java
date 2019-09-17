package com.example.minimalrisk;

import java.util.Objects;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import com.google.gson.Gson;

class Board {

    Graph g;

    Board(String countryGraphString) {
        this.g = new Graph();
        Gson gson = new Gson();
        GsonTemplateCountryGraph countryGraphObject = gson.fromJson(countryGraphString, GsonTemplateCountryGraph.class);
        for (GsonTemplateContinent continent : countryGraphObject.continents) {
            for (GsonTemplateCountry country : continent.countries) {
                this.g.addNode(new Node(country.name, continent.name, country.player, country.count));
            }
        }
        for (GsonTemplateBidirectionalLink link : countryGraphObject.bidirectionalLinks) {
            this.g.addEdge(new Edge(this.g.getNode(link.fromCountry), this.g.getNode(link.toCountry)));
        }
    }

    static String getCountryListJSON(ArrayList<Node> nodes) {
        GsonTemplateCountryList countryListObject = new GsonTemplateCountryList();
        for (Node n : nodes) {
            countryListObject.countries.add(new GsonTemplateCountry(n.getName(), n.getPlayer(), n.getCount(), n.isModified()));
        }
        Gson gson = new Gson();
        return gson.toJson(countryListObject);
    }

    String getCountryGraphJSON() {
        GsonTemplateCountryGraph countryGraphObject = new GsonTemplateCountryGraph();
        for (String continentName : g.getAllNodeGroups()) {
            ArrayList<GsonTemplateCountry> countries = new ArrayList<>();
            for (Node n : g.getAllNodes(continentName)) {
                countries.add(new GsonTemplateCountry(n.getName(), n.getPlayer(), n.getCount(), n.isModified()));
            }
            countryGraphObject.continents.add(new GsonTemplateContinent(continentName, countries));
        }                        
        for (Edge e : g.getAllBidirectionalLinks()) {
            countryGraphObject.bidirectionalLinks.add(new GsonTemplateBidirectionalLink(e.getSource().getName(), e.getDestination().getName())); 
        }                        
        Gson gson = new Gson();
        return gson.toJson(countryGraphObject);
    }

    void resetModified() {
        Set<Node> nodes = this.g.getAllNodes();
        for (Node node : nodes) {
            node.resetModified();
        }
    }

    boolean gameOver() {
        // System.out.println("game over?");
        Set<String> nodeGroups = this.g.getAllNodeGroups();
        Set<String> players =  this.g.getAllPlayers(); 
        // System.out.println("node groups: " + nodeGroups);
        // System.out.println("players: " + players);
        for (String nodeGroup : nodeGroups) {
            for (String player : players) {
                // System.out.println("node count " + nodeGroup + ", " + player + ": " + this.g.nodeCount(nodeGroup, player));
                if (this.g.nodeCount(nodeGroup, player) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    void initialAllocationOfCountries(String playerA, String playerB) {
        // assuming number of nodes is even, each player must have equal number of countries
        Set<Node> nodes = this.g.getAllNodes();
        int nodeCount = nodes.size();
        // integer division (no remainder)
        int nodeCountPlayerA = nodeCount / 2;
        int nodeCountPlayerB = nodeCount - nodeCountPlayerA;
        ArrayList<String> draws = new ArrayList<>();
        for (int i = 0; i < nodeCountPlayerA; i++) {
            draws.add(playerA);
        }
        for (int i = 0; i < nodeCountPlayerB; i++) {
            draws.add(playerB);
        }
        Collections.shuffle(draws);
        for (Node node : nodes) {
            String player = draws.remove(0);
            node.setPlayer(player);
            node.setCount(1);
        }
    }

    void allocationOfExtraTroops(String player, int extraTroops) {
        // System.out.println("allocating " + extraTroops + " troops to " + player);
        Random randomGenerator = new Random();
        Set<Node> nodes = this.g.getAllNodes();
        ArrayList<Node> nodesPlayer = new ArrayList<>();
        for (Node node : nodes) {
            if (Objects.equals(node.getPlayer(), player)) {
                nodesPlayer.add(node);
            }
        }
        if (nodesPlayer.size() > 0) {
            for (int i = 0; i < extraTroops; i++) {
                int randomIndex = randomGenerator.nextInt(nodesPlayer.size());
                Node node = nodesPlayer.get(randomIndex);
                // System.out.println("old count node " + node.getName() + ": " + node.getCount());
                node.incrementCount();
                // System.out.println("new count node " + node.getName() + ": " + node.getCount());
            }
        }
    }

    void initialAllocationOfExtraTroops(int extraTroopsEach) {
        Set<String> players = this.g.getAllPlayers();
        for (String player : players) {
            allocationOfExtraTroops(player, extraTroopsEach);
        }
    }

    ArrayList<Node> possibleAttackingCountries(String attackingPlayer, Node targetCountry) {
        ArrayList<Node> possibleAttackingCountries = new ArrayList<>(); 
        HashSet<Node> neighbors = this.g.childrenOf(targetCountry);
        // System.out.println("neighbors of target country " + targetCountry + ": " + neighbors);
        for (Node neighbor : neighbors) {
            // System.out.println("Objects.equals(neighbor.getPlayer(), attackingPlayer): " + Objects.equals(neighbor.getPlayer(), attackingPlayer));
            if (Objects.equals(neighbor.getPlayer(), attackingPlayer) && neighbor.getCount() > 1) {
                possibleAttackingCountries.add(neighbor);
            }
        }
        return possibleAttackingCountries;
    }

    ArrayList<Node> possibleTargetCountries(String attackingPlayer) {
        ArrayList<Node> possibleTargetCountries = new ArrayList<>(); 
        ArrayList<Node> enemyCountries = new ArrayList<>(); 
        Set<Node> nodes = this.g.getAllNodes();
        for (Node node : nodes) {
            if (! Objects.equals(node.getPlayer(), attackingPlayer)) {
                enemyCountries.add(node);
            }
        }
        // System.out.println("possibe target countries, candidates: " + enemyCountries);
        for (Node enemyCountry : enemyCountries) {
            ArrayList<Node> possibleAttackingCountries = possibleAttackingCountries(attackingPlayer, enemyCountry);
            if (possibleAttackingCountries.size() > 0) {
                possibleTargetCountries.add(enemyCountry);
            }
        }
        return possibleTargetCountries;
    }

    ArrayList<Node> possibleTargetCountries(String attackingPlayer, Node attackingCountry) {
        ArrayList<Node> possibleTargetCountries = new ArrayList<>(); 
        ArrayList<Node> candidateCountries = possibleTargetCountries(attackingPlayer);
        for (Node candidateCountry : candidateCountries) {
            if (this.g.shortestPath(attackingCountry, candidateCountry, attackingPlayer).size() > 0) {
                possibleTargetCountries.add(candidateCountry);
            }
        }
        return possibleTargetCountries;
    }

    void battle(Node attackingCountry, Node targetCountry) {
        // System.out.println("battle: " + attackingCountry.getName() + " is attacking " + targetCountry.getName());
        Random randomGenerator = new Random();
        attackingCountry.setCount(1);
        // later different counts possible
        targetCountry.setCount(1);
        String[] players = {attackingCountry.getPlayer(), targetCountry.getPlayer()};
        int randomIndex = randomGenerator.nextInt(players.length);
        String winner = players[randomIndex];
        if (Objects.equals(winner, attackingCountry.getPlayer())) {
            targetCountry.setPlayer(winner);
        }
    }

    void attack(String attackingPlayer) {
        // System.out.println(attackingPlayer + " attacking");
        Random randomGenerator = new Random();
        ArrayList<Node> possibleTargetCountries = possibleTargetCountries(attackingPlayer);
        // System.out.println("possible target countries: " + possibleTargetCountries);
        if (possibleTargetCountries.size() > 0) {
            int randomIndex = randomGenerator.nextInt(possibleTargetCountries.size());
            Node targetCountry = possibleTargetCountries.get(randomIndex);
            // System.out.println("target country: " + targetCountry);
            ArrayList<Node> possibleAttackingCountries = possibleAttackingCountries(attackingPlayer, targetCountry);
            // System.out.println("possible attacking countries for target country " + targetCountry + ": " + possibleAttackingCountries);
            if (possibleAttackingCountries.size() > 0)  {
                randomIndex = randomGenerator.nextInt(possibleAttackingCountries.size());
                Node attackingCountry = possibleAttackingCountries.get(randomIndex);
                // System.out.println("attacking country: " + attackingCountry);
                // System.out.println("battle " + attackingCountry + " against " + targetCountry);
                battle(attackingCountry, targetCountry);
            }
        }
    }

    ArrayList<Node> possibleSources(String player) {
        ArrayList<Node> possibleSources = new ArrayList<>() ;
        Set<Node> nodes = this.g.getAllNodes();
        for (Node node : nodes) {
            if (Objects.equals(node.getPlayer(), player) && node.getCount() > 1) {
                possibleSources.add(node);
            }
        }
        return possibleSources;
    }

    ArrayList<Node> possibleSources(String player, String destination) {
        ArrayList<Node> possibleSources = new ArrayList<>(); 
        ArrayList<Node> candidates = possibleSources(player);
        for (Node candidate : candidates) {
            if (this.g.shortestPath(candidate, getNode(destination), player).size() > 0) {
                possibleSources.add(candidate);
            }
        }
        return possibleSources;
    }

    ArrayList<Node> possibleDestinations(String player) {
        ArrayList<Node> possibleDestinations = new ArrayList<>() ;
        Set<Node> nodes = this.g.getAllNodes();
        for (Node node : nodes) {
            if (Objects.equals(node.getPlayer(), player)) {
                possibleDestinations.add(node);
            }
        }
        return possibleDestinations;
    }

    ArrayList<Node> possibleDestinations(String player, String source) {
        ArrayList<Node> possibleDestinations = new ArrayList<>() ;
        ArrayList<Node> candidates = possibleDestinations(player);
        for (Node candidate : candidates) {
            if (this.g.shortestPath(getNode(source), candidate, player).size() > 0) {
                possibleDestinations.add(candidate);
            }
        }
        return possibleDestinations;
    }

    void moveTroops(String player) {
        ArrayList<Node> possibleSources = possibleSources(player);
        ArrayList<Node> possibleDestinations = possibleDestinations(player);
        if (possibleSources.size() > 0 && possibleDestinations.size() > 0) {
            for (Node possibleSource : possibleSources) {
                for (Node possibleDestination : possibleDestinations) {
                    if (possibleDestination != possibleSource
                        && this.g.shortestPath(possibleSource, possibleDestination, player).size() > 0) {
                        possibleDestination.setCount(possibleDestination.getCount() + possibleSource.getCount() - 1);
                        possibleSource.setCount(1);
                        return;
                    }
                }
            }
        }
    }

    void nextMove(String player) {
        allocationOfExtraTroops(player, 2);
        attack(player);
        moveTroops(player);
    }

    ArrayList<Node> shortestPath(String fromName, String toName, String player) {
        Node fromNode = this.g.getNode(fromName);
        Node toNode = this.g.getNode(toName);
        return this.g.shortestPath(fromNode, toNode, player);
    }

    Node getNode(String name) {
        return this.g.getNode(name);
    }

    void printNodes() {
        System.out.println("\nNodes:\n");
        Set<Node> nodes = this.g.getAllNodes();
        List<Node> nodeList = new ArrayList<>(nodes);
        Collections.sort(nodeList);
        for (Node node : nodeList) {
            System.out.println(node);
        }
        System.out.println("\n");
    }

}

