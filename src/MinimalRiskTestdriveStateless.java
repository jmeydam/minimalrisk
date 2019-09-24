import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import com.example.minimalrisk.MinimalRisk;

/**
 * Example for the use of the MinimalRisk API. 
 */
public class MinimalRiskTestdriveStateless {

    public static void main(String[] args) {
        try {
            String initConfigFile = "json/country_graph_example.json";

            System.out.println("\nReading " + initConfigFile + "\n");
            Path path = FileSystems.getDefault().getPath(initConfigFile);
            String countryGraph = Files.readString(path, StandardCharsets.UTF_8);

            System.out.println("\nInitial configuration of board:\n");
            System.out.println(countryGraph);

            countryGraph = MinimalRisk.initialAllocationOfCountries(countryGraph, "A", "B");
            System.out.println("\nAfter initialAllocationOfCountries:\n");
            System.out.println(countryGraph);
            
            countryGraph = MinimalRisk.initialAllocationOfExtraTroops(countryGraph, 6);
            System.out.println("\nAfter initialAllocationOfExtraTroops:\n");
            System.out.println(countryGraph);

            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 0) {
                    System.out.println("\n\n*************************************************\n");
                    System.out.println("\nRound " + (i / 2 + 1) + " ...\n");
                    System.out.println("\nA\'s turn\n");
                    // nextMove("A");
                    countryGraph = MinimalRisk.allocationOfExtraTroops(countryGraph, "A", 2);
                    System.out.println("\nAfter allocationOfExtraTroops:\n");
                    System.out.println(countryGraph);
                    countryGraph = MinimalRisk.attack(countryGraph, "A");
                    System.out.println("\nAfter attack:\n");
                    System.out.println(countryGraph);
                    countryGraph = MinimalRisk.moveTroops(countryGraph, "A");
                    System.out.println("\nAfter moveTroops:\n");
                    System.out.println(countryGraph);
                } else {
                    System.out.println("\nB\'s turn\n");
                    // nextMove("B");
                    countryGraph = MinimalRisk.allocationOfExtraTroops(countryGraph, "B", 2);
                    System.out.println("\nAfter allocationOfExtraTroops:\n");
                    System.out.println(countryGraph);
                    countryGraph = MinimalRisk.attack(countryGraph, "B");
                    System.out.println("\nAfter attack:\n");
                    System.out.println(countryGraph);
                    countryGraph = MinimalRisk.moveTroops(countryGraph, "B");
                    System.out.println("\nAfter moveTroops:\n");
                    System.out.println(countryGraph);
                }
                System.out.println("\nGame over?\n");
                System.out.println(countryGraph);
                if (MinimalRisk.gameOver(countryGraph)) {
                    System.out.println("\nGAME OVER!\n");
                    break;
                } else {
                    System.out.println("\nNo.\n");
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println();
        }
    }

}

