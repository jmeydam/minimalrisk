import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import com.example.minimalrisk.GsonTemplateCountryGraph;
import com.example.minimalrisk.GsonTemplateCountryList;

class GsonTest {

    public static void main(String[] args) throws IOException {

        System.out.println("\n------------- Country graph -------------\n");

        Path path = FileSystems.getDefault().getPath("country_graph_init.json");
        String countryGraphString = Files.readString(path, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        GsonTemplateCountryGraph countryGraphObject = gson.fromJson(countryGraphString, GsonTemplateCountryGraph.class);
        String json = gson.toJson(countryGraphObject);
        System.out.println(json);

        System.out.println("\n------------- Country list -------------\n");

        System.out.println("Gson with json from file:");
        Path path2 = FileSystems.getDefault().getPath("country_list_example.json");
        String countryListString = Files.readString(path2, StandardCharsets.UTF_8);
        Gson gson2 = new Gson();
        GsonTemplateCountryList countryListObject = gson2.fromJson(countryListString, GsonTemplateCountryList.class);
        String json2 = gson2.toJson(countryListObject);
        System.out.println(json2);

    }

}
