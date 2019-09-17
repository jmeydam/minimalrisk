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


            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 0) {
                    System.out.println("\nRound " + (i + 1) + " ...\n");
                    System.out.println("A\'s turn:\n");
                    // nextMove("A");
                    countryGraph = MinimalRisk.allocationOfExtraTroops(countryGraph, "A", 2);
                    countryGraph = MinimalRisk.attack(countryGraph, "A");
                    countryGraph = MinimalRisk.moveTroops(countryGraph, "A");
                } else {
                    System.out.println("\nB\'s turn:\n");
                    // nextMove("B");
                    countryGraph = MinimalRisk.allocationOfExtraTroops(countryGraph, "B", 2);
                    countryGraph = MinimalRisk.attack(countryGraph, "B");
                    countryGraph = MinimalRisk.moveTroops(countryGraph, "B");
                }
                System.out.println(countryGraph);
                if (MinimalRisk.gameOver(countryGraph)) {
                    System.out.println("\nGAME OVER!\n");
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println();
        }
    }

}

