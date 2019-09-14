package com.example.minimalrisk;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

class DirectedGraph {

    private Map<Node, HashSet<Node>> edges;

    DirectedGraph() {
        this.edges = new HashMap<>();
    }

    void addNode(Node node) {
        if (! this.edges.containsKey(node)) {
            this.edges.put(node, new HashSet<Node>());
        }
    }

    void addEdge(Edge edge) {
        Node source = edge.getSource();
        Node destination = edge.getDestination();
        // ensure that all nodes - also destinations - are present as key
        if (this.edges.containsKey(source) && this.edges.containsKey(destination)) {
            this.edges.get(source).add(destination);
        }
    }

    HashSet<Node> childrenOf(Node node) {
        return this.edges.get(node);
    }

    boolean hasNode(Node node) {
        return this.edges.containsKey(node);
    }

    Node getNode(String name) {
        for (Node node : this.edges.keySet()) {
            if (node.getName() == name) {
                return node;
            }
        }
        return null;
    }

    Set<Node> getAllNodes() {
        return this.edges.keySet();
    }

    Set<String> getAllNodeGroups() {
        HashSet<String> nodeGroupSet = new HashSet<>();
        for (Node node : getAllNodes()) {
            nodeGroupSet.add(node.getNodeGroup());
        }
        return nodeGroupSet;
    }

    Set<String> getAllPlayers() {
        HashSet<String> playerSet = new HashSet<>();
        for (Node node : getAllNodes()) {
            playerSet.add(node.getPlayer());
        }
        return playerSet;
    }

}

