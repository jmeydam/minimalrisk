import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import com.example.minimalrisk.MinimalRisk;

public class MinimalRiskTestdriveStateless {

    public static void main(String[] args) {
        try {
            String initConfigFile = "country_graph_init.json";

            System.out.println("Reading " + initConfigFile);
            Path path = FileSystems.getDefault().getPath(initConfigFile);
            String countryGraph = Files.readString(path, StandardCharsets.UTF_8);

            System.out.println("Initial configuration of board");
            System.out.println(countryGraph);

            countryGraph = MinimalRisk.initialAllocationOfCountries(countryGraph, "A", "B");
            System.out.println("Initial allocation of countries");
            System.out.println(countryGraph);
            
            countryGraph = MinimalRisk.initialAllocationOfExtraTroops(countryGraph, 6);
            System.out.println("Initial allocation of extra troops");
            System.out.println(countryGraph);

            /*
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
            */
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println();
            
        }
    }

}

