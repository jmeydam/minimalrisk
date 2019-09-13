package com.example.minimalrisk;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.Collections;

class Board {

    Graph g;

    Board(String countryGraphString) {
        this.g = Graph();
        // ignore parameter for now, read file here
        Path path = FileSystems.getDefault().getPath("country_graph_init.json");
        String countryGraphString = Files.readString(path, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        GsonTemplate countryGraphObject = gson.fromJson(countryGraphString, GsonTemplate.class);
        for (GsonTemplateContinent continent : countryGraphObject.continents) {
            for (GsonTemplateCountry country : continent.countries) {
                this.g.addNode(new Node(country.name, continent.name));
        for (GsonTemplateBidirectionalLink link : countryGraphObject.bidirectionalLinks) {
            this.g.addEdge(new Edge(this.g.getNode(link.fromCountry), this.g.getNode(link.toCountry)));
        }
    }

    boolean gameOver():
        Set<String> nodeGroups = this.g.getAllNodeGroups();
        Set<String> players =  this.g.getAllPlayers(); 
        for (String nodeGroup : nodeGroups) {
            for (String player : playeers) {
                if (this.g.nodeCount(nodeGroup, player) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    ArrayList<String> shortestPath(String fromName, String toName) {
        Node fromNode = this.g.getNode(fromName);
        Node toNode = this.g.getNode(toName);
        ArrayList<Node> nodeList = this.g.shortestPathBFS(fromNode, toNode);
        ArrayList<String> stringList = new ArrayList<>; 
        for (Node node : nodeList) {
            stringList.add(node.getName());
        }
        return stringList;
    }

    void initialAllocationOfCountries(String playerA, String playerB) {
        // assuming number of nodes is even, each player must have equal number of countries
        Set<Node> nodes = this.g.getAllNodes();
        int nodeCount = nodes.size();
        // integer division (no remainder)
        int nodeCountPlayerA = nodeCount / 2;
        int nodeCountPlayerB = nodeCount - nodeCountPlayerA;
        ArrayList<String> draws = new ArrayList();
        for (int i = 0; i < nodeCountPlayerA; i++) {
            draws.add(playerA);
        }
        for (int i = 0; i < nodeCountPlayerB; i++) {
            draws.add(playerB);
        }
        random.shuffle(draws)
        Collections.shuffle(draws);
        for (Node node : nodes) {
            player = draws.remove(0);
            node.setPlayer(player);
            node.setCount(1);
        }
    }

////////////
    void initialAllocationOfExtraTroops(extraTroopsEach) {
        nodes = this.g.getAllNodes()
        players = set([node.getPlayer() for node in nodes])
        for player in players:
            nodesPlayer = [node for node in nodes if node.getPlayer() == player]
            for _ in range(extraTroopsEach):
                node = random.choice(nodesPlayer)
                node.incrementCount()
    }

    void allocationOfExtraTroops(player, extraTroops) {
        nodes = this.g.getAllNodes()
        nodesPlayer = [node for node in nodes if node.getPlayer() == player]
        for _ in range(extraTroops):
            node = random.choice(nodesPlayer)
            print("Incrementing troops in " + str(node) + " ...")
            node.incrementCount()
    }

    ArrayList<Node> possibleAttackingCountries(attackingPlayer, targetCountry) {
        possibleAttackingCountries = []
        neighbors = this.g.childrenOf(targetCountry)
        for neighbor in neighbors:
            if neighbor.getPlayer() == attackingPlayer and neighbor.getCount() > 1:
               possibleAttackingCountries.append(neighbor)
        return possibleAttackingCountries
    }

    ArrayList<Node> possibleTargetCountries(attackingPlayer) {
        possibleTargetCountries = []
        nodes = this.g.getAllNodes()
        enemyCountries = [node for node in nodes if node.getPlayer() != attackingPlayer]
        for enemyCountry in enemyCountries:
            possibleAttackingCountries = this.possibleAttackingCountries(attackingPlayer, enemyCountry)
            if len(possibleAttackingCountries) > 0:
                possibleTargetCountries.append(enemyCountry)
        return possibleTargetCountries
    }

    void battle(attackingCountry, targetCountry) {
        attackingCountry.setCount(1)
        // later different counts possible
        targetCountry.setCount(1)
        players = [attackingCountry.getPlayer(), targetCountry.getPlayer()]
        winner = random.choice(players)
        if winner == attackingCountry.getPlayer():
            targetCountry.setPlayer(winner)
    }

    void attack(attackingPlayer) {
        possibleTargetCountries = this.possibleTargetCountries(attackingPlayer)
        if len(possibleTargetCountries) > 0:
            targetCountry = random.choice(possibleTargetCountries)
            possibleAttackingCountries = this.possibleAttackingCountries(attackingPlayer, targetCountry)
            if len(possibleAttackingCountries) > 0:
                attackingCountry = random.choice(possibleAttackingCountries)
                // attack always with maximum number of allowable troops
                print(str(attackingCountry) + " attacking " + str(targetCountry) + " ...")
                this.battle(attackingCountry, targetCountry)
    }

    ArrayList<Node> possibleSources(movingPlayer) {
        nodes = this.g.getAllNodes()
        return [node for node in nodes if node.getPlayer() == movingPlayer and node.getCount() > 1]

    ArrayList<Node> possibleDestinations(movingPlayer):
        nodes = this.g.getAllNodes()
        return [node for node in nodes if node.getPlayer() == movingPlayer]
    }

    void moveTroops(player) {
        possibleSources = this.possibleSources(player)
        possibleDestinations = this.possibleDestinations(player)
        if len(possibleSources) > 0 and len(possibleDestinations) > 0:
            for possibleSource in possibleSources:
                for possibleDestination in possibleDestinations:
                    if possibleDestination != possibleSource \
                    and len(this.shortestPath(possibleSource.getName(),possibleDestination.getName())) > 0:
                        print("Moving troops from " + str(possibleSource) + " to " + str(possibleDestination) + " ...")
                        possibleDestination.setCount(possibleDestination.getCount() + possibleSource.getCount() - 1)
                        possibleSource.setCount(1)
                        return
    }

    void nextMove(player) {
        this.allocationOfExtraTroops(player, extraTroops=2)
        this.attack(attackingPlayer=player)
        this.moveTroops(player)
    }




}

