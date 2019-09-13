package com.example.minimalrisk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

class Graph extends DirectedGraph {

    Graph() {
        super();
    }

    void addEdge(Edge edge) {
        super.addEdge(edge);
        Edge reverse = new Edge(edge.getDestination(), edge.getSource());
        super.addEdge(reverse);
    }

    int nodeCount(String nodeGroup, String player) {
        int count = 0;
        Set<Node> nodes = this.getAllNodes();
        for (Node node : nodes) {
            if (node.getNodeGroup() == nodeGroup && node.getPlayer() == player) {
                count++;
            }
        }
        return count;
    } 

    ArrayList<Node> shortestPathBFS(Node start, Node end) {
        ArrayList<Node> initPath = new ArrayList<>();
        initPath.add(start);
        LinkedList<ArrayList<Node>> pathQueue = new LinkedList<ArrayList<Node>>();
        pathQueue.add(initPath);
        while (pathQueue.size() > 0) {
            ArrayList<Node> newPathTrunk = pathQueue.removeFirst();
            Node lastNode = newPathTrunk.get(newPathTrunk.size() - 1);
            if (lastNode == end) {
                return newPathTrunk;
            }
            for (Node nextNode : this.childrenOf(lastNode)) {
                if (! newPathTrunk.contains(nextNode)) {
                    ArrayList<Node> newPath = new ArrayList<>();
                    newPath.addAll(newPathTrunk);
                    newPath.add(nextNode);
                    pathQueue.add(newPath);
                }
            }
        }
        return null;
    }

}

