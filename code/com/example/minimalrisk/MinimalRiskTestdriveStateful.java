package com.example.minimalrisk;

public class MinimalRiskTestdriveStateful {

    public static void main(String[] args) {
        Board board;
        try {
            board = new Board();
            board.initialAllocationOfCountries("A", "B");
            System.out.println("\nInitial allocation of extra troops ...\n");
            board.initialAllocationOfExtraTroops(6);
            board.printNodes();
            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 0) {
                    System.out.println("Round " + (i + 1) + " ...\n");
                    System.out.println("A\'s turn:\n");
                    board.nextMove("A");
                } else {
                    System.out.println("B\'s turn:\n");
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

