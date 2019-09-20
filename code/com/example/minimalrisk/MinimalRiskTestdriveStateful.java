package com.example.minimalrisk;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Example for the use of the implementation classes. Can be adapted for tests.
 */
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
            System.out.println("Initial allocation of extra troops ...");
            board.initialAllocationOfExtraTroops(6);
            System.out.println(board.nodesSummary());
            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 0) {
                    System.out.println("Round " + (i / 2 + 1) + " ...\n");
                    System.out.println("A\'s turn\n");
                    board.nextMove("A");
                } else {
                    System.out.println("B\'s turn\n");
                    board.nextMove("B");
                }
                System.out.println(board.nodesSummary());
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

