package com.example.minimalrisk;

import java.util.Objects;

class Edge implements Comparable {
    
    private final Node source; 
    private final Node destination;

    Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    Node getSource() {
        return this.source;
    }

    Node getDestination() {
        return this.destination;
    }

    public String toString() {
        return this.source.getName() + " -> " + this.destination.getName();
    }

    public int hashCode() {
        return Objects.hash(this.source, this.destination);
    }

    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (this.getClass() != otherObject.getClass()) return false;
        Edge other = (Edge) otherObject;
        return this.source == other.source && this.destination == other.destination;
    }

    public int compareTo(Object otherObject) {
        Edge other = (Edge) otherObject;
        int i = this.source.compareTo(other.source);
        if (i != 0) {
            return i;
        }
        return this.destination.compareTo(other.destination);
    }

}

