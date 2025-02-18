import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParseJson {
    private List<String> jsonStringList;
    private List<StationDepth> stationsDepth;
    private String sameNameSmolenskaya = "Смоленская";
    private String sameNameArbatskaya = "Арбатская";

    public ParseJson() {
        parseJson();
        filtredJson();
    }

    public List<String> splitJsonFile() {
        jsonStringList = new ArrayList<>();
        SearchFiles searchFiles = new SearchFiles();
        String[] splitedJSONFiles = searchFiles.getJsonFilesAbsolutePath().split("\n");
        jsonStringList = Stream.of(splitedJSONFiles).map(path -> {
            try {
                return Files.readString(Paths.get(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        return jsonStringList;
    }

    private void parseJson() {

        stationsDepth = new ArrayList<>();
        for (String jsonObject : splitJsonFile()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(jsonObject);
                for (JsonNode object : jsonNode) {
                    String stationName = object.get("station_name").asText();
                    String stationDepth = object.get("depth").asText();
                    String depth = stationDepth.replaceAll(",", ".").replaceAll("\\?", "-0");
                    stationsDepth.add(new StationDepth(stationName, depth));
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void filtredJson() {
        for (int i = 0; i < stationsDepth.size(); i++) {
            String name = stationsDepth.get(i).getName();
            Double depth = Double.parseDouble(stationsDepth.get(i).getDepth());
            for (int j = 0; j < stationsDepth.size(); j++) {
                String anotherName = stationsDepth.get(j).getName();
                Double anotherDepth = Double.parseDouble(stationsDepth.get(j).getDepth());
                if (name.equals(anotherName) && !name.equals(sameNameSmolenskaya) && !name.equals(sameNameArbatskaya)) {
                    if (depth.compareTo(anotherDepth) > 0) {
                        stationsDepth.remove(j);
                    } else {
                        stationsDepth.remove(i);
                    }
                }
            }
        }
    }

    public List<StationDepth> getStationsDepth() {
        return stationsDepth;
    }


}
