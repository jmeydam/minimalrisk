import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import com.example.minimalrisk.GsonTemplate;

class GsonTest {

    public static void main(String[] args) throws IOException {
        System.out.println("Gson with template object:");
        Gson gson = new Gson();
        String json = gson.toJson(new GsonTemplate());
        System.out.println(json);

        System.out.println("Gson with json from file:");
        Path path = FileSystems.getDefault().getPath("country_graph_init.json");
        String countryGraphString = Files.readString(path, StandardCharsets.UTF_8);
        Gson gson2 = new Gson();
        GsonTemplate countryGraphObject = gson2.fromJson(countryGraphString, GsonTemplate.class);
        String json2 = gson2.toJson(countryGraphObject);
        System.out.println(json2);
    }

}
