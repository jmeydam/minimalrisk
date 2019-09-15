package com.example.minimalrisk;

import java.util.Objects;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

class Board {

    Graph g;

    Board(String countryGraphString) {
        this.g = new Graph();
        Gson gson = new Gson();
        GsonTemplateCountryGraph countryGraphObject = gson.fromJson(countryGraphString, GsonTemplateCountryGraph.class);
        for (GsonTemplateContinent continent : countryGraphObject.continents) {
            for (GsonTemplateCountry country : continent.countries) {
                this.g.addNode(new Node(country.name, continent.name));
            }
        }
        for (GsonTemplateBidirectionalLink link : countryGraphObject.bidirectionalLinks) {
            this.g.addEdge(new Edge(this.g.getNode(link.fromCountry), this.g.getNode(link.toCountry)));
        }
    }

    Board() throws IOException {
        this(countryGraphStringExample());
    }

    static String countryGraphStringExample() throws IOException {
        Path path = FileSystems.getDefault().getPath("country_graph_init.json");
        String countryGraphString = Files.readString(path, StandardCharsets.UTF_8);
        return countryGraphString;
    }

    boolean gameOver() {
        Set<String> nodeGroups = this.g.getAllNodeGroups();
        Set<String> players =  this.g.getAllPlayers(); 
        for (String nodeGroup : nodeGroups) {
            for (String player : players) {
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
                node.incrementCount();
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
        for (Node neighbor : neighbors) {
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
            if (node.getPlayer() != attackingPlayer) {
                enemyCountries.add(node);
            }
        }
        for (Node enemyCountry : enemyCountries) {
            ArrayList<Node> possibleAttackingCountries = possibleAttackingCountries(attackingPlayer, enemyCountry);
            if (possibleAttackingCountries.size() > 0) {
                possibleTargetCountries.add(enemyCountry);
            }
        }
        return possibleTargetCountries;
    }

    void battle(Node attackingCountry, Node targetCountry) {
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
        Random randomGenerator = new Random();
        ArrayList<Node> possibleTargetCountries = possibleTargetCountries(attackingPlayer);
        if (possibleTargetCountries.size() > 0) {
            int randomIndex = randomGenerator.nextInt(possibleTargetCountries.size());
            Node targetCountry = possibleTargetCountries.get(randomIndex);
            ArrayList<Node> possibleAttackingCountries = possibleAttackingCountries(attackingPlayer, targetCountry);
            if (possibleAttackingCountries.size() > 0)  {
                randomIndex = randomGenerator.nextInt(possibleAttackingCountries.size());
                Node attackingCountry = possibleAttackingCountries.get(randomIndex);
                battle(attackingCountry, targetCountry);
            }
        }
    }

    ArrayList<Node> possibleSources(String movingPlayer) {
        ArrayList<Node> possibleSources = new ArrayList<>() ;
        Set<Node> nodes = this.g.getAllNodes();
        for (Node node : nodes) {
            if (Objects.equals(node.getPlayer(), movingPlayer) && node.getCount() > 1) {
                possibleSources.add(node);
            }
        }
        return possibleSources;
    }

    ArrayList<Node> possibleDestinations(String movingPlayer) {
        ArrayList<Node> possibleDestinations = new ArrayList<>() ;
        Set<Node> nodes = this.g.getAllNodes();
        for (Node node : nodes) {
            if (Objects.equals(node.getPlayer(), movingPlayer)) {
                possibleDestinations.add(node);
            }
        }
        return possibleDestinations;
    }

    ArrayList<String> shortestPath(String fromName, String toName, String player) {
        Node fromNode = this.g.getNode(fromName);
        Node toNode = this.g.getNode(toName);
        ArrayList<Node> nodeList = this.g.shortestPath(fromNode, toNode, player);
        ArrayList<String> stringList = new ArrayList<>(); 
        for (Node node : nodeList) {
            stringList.add(node.getName());
        }
        return stringList;
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

