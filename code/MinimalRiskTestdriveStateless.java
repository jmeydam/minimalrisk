import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import com.example.minimalrisk.MinimalRisk;

public class MinimalRiskTestdriveStateless {

    public static void main(String[] args) {
        try {
            System.out.println("Reading country_graph_init.json");
            Path path = FileSystems.getDefault().getPath("country_graph_init.json");
            String initialConfig = Files.readString(path, StandardCharsets.UTF_8);
            System.out.println(initialConfig);
            String initialAllocation = MinimalRisk.initialAllocationOfCountries(initialConfig, "A", "B");
            System.out.println(initialAllocation);
            
            /*
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
            */
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println();
            
        }
    }

}

