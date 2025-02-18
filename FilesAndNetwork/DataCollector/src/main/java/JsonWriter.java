import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.IOException;

public class JsonWriter {
    public static void writer(JSONObject object, String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File output = new File(path);
            mapper.writerWithDefaultPrettyPrinter().writeValue(output, object);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
