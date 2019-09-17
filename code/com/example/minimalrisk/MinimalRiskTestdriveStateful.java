package com.example.minimalrisk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class MinimalRiskTestdriveStateful {

    static String countryGraphStringExample() throws IOException {
        Path path = FileSystems.getDefault().getPath("country_graph_example.json");
        String countryGraphString = Files.readString(path, StandardCharsets.UTF_8);
        return countryGraphString;
    }

    public static void main(String[] args) throws IOException {

        Board board;
        try {
            board = new Board(MinimalRiskTestdriveStateful.countryGraphStringExample());
            board.initialAllocationOfCountries("A", "B");
            System.out.println("\nInitial allocation of extra troops ...\n");
            board.initialAllocationOfExtraTroops(6);
            board.printNodes();
            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 0) {
                    System.out.println("\nRound " + (i / 2 + 1) + " ...\n\n");
                    System.out.println("A\'s turn:");
                    board.nextMove("A");
                } else {
                    System.out.println("B\'s turn:");
                    board.nextMove("B");
                }
                board.printNodes();
                if (board.gameOver()) {
                    System.out.println("GAME OVER!\n");
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println();
            
        }
    }

}

