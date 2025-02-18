import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MetroMoscowMapJson {
    private JSONObject mainObject;
    private JSONObject stationsObjectJson;
    private JSONArray linesArrayJson;
    private LinkedHashMap<String, String> stationsPerLine;

    private ParseHtml parseHtml = new ParseHtml();
    private List<Station> stations = parseHtml.getStations();
    private List<Line> lines = parseHtml.getLines();

    public MetroMoscowMapJson() {
        createJsonObject();
    }


    public LinkedHashMap<String, String> getStationsPerLine() {
        stationsPerLine = new LinkedHashMap<>();

        for (int i = 0; i < stations.size(); i++) {
            if (!stationsPerLine.containsKey(stations.get(i).getLine())) {
                stationsPerLine.put(stations.get(i).getLine(), "");
            }
            stationsPerLine.put(stations.get(i).getLine(),
                    stationsPerLine.get(stations.get(i).getLine()) + "  " + stations.get(i).getName());
        }
        return stationsPerLine;
    }

    private JSONObject createJsonObject() {
        String keyStations = "stations";
        String keyLines = "lines";
        getStationsPerLine();
        mainObject = new JSONObject();


        stationsObjectJson = new JSONObject();
        for (int i = 0; i < lines.size(); i++) {
            ArrayList<String> stationsArray = new ArrayList<>();
            String listStations = stationsPerLine.get(lines.get(i).getNumber()).trim();
            String[] fragments = listStations.split("\\s{2}");
            for (String fragment : fragments) {
                stationsArray.add(fragment);
            }
            stationsObjectJson.put(lines.get(i).getNumber(), stationsArray);
        }

        linesArrayJson = new JSONArray();
        for (int i = 0; i < lines.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("number", lines.get(i).getNumber());
            obj.put("name", lines.get(i).getName());
            linesArrayJson.add(obj);
        }

        mainObject.put(keyStations, stationsObjectJson);
        mainObject.put(keyLines, linesArrayJson);
        return mainObject;
    }
    public JSONObject getMainObject() {
        return mainObject;
    }





}
