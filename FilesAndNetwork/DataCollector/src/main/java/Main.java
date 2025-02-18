import org.json.simple.JSONObject;

public class Main{
    public static void main(String[] args) {
        String pathMapMoscow = "data/map.json";
        String pathStationsInfo = "data/stations.json";

        MetroMoscowMapJson metroMoscowMapJson = new MetroMoscowMapJson();
        JsonStations jsonStations = new JsonStations();

        JSONObject objMapMoscow = metroMoscowMapJson.getMainObject();
        JSONObject objStationsInfo = jsonStations.getMainObject();

        JsonWriter.writer(objMapMoscow, pathMapMoscow);
        JsonWriter.writer(objStationsInfo, pathStationsInfo);


//        ParseHtml parseHtml = new ParseHtml();
//        System.out.println(parseHtml.getLines());
//
//        SearchFiles searchFiles = new SearchFiles();
//        System.out.println(searchFiles.getCsvFilesAbsolutePath());
//
//        System.out.println(searchFiles.getJsonFilesAbsolutePath());
//        ParseJson parseJSON = new ParseJson();
//        System.out.println(parseJSON.splitJsonFile().get(1));
//        System.out.println("   -------- ");
//        System.out.println(parseJSON.splitJsonFile().get(2));
//
//        System.out.println(parseJSON.getStationsDepth());
//
//        ParseCsv parseCSV = new ParseCsv();
//        System.out.println(parseCSV.splitCsvFile().get(10));
//        System.out.println("   -------- ");
//        System.out.println(parseCSV.splitCsvFile().get(2));
//
//        System.out.println(parseCSV.getStationsDates());
//        MetroMoscowMapJson metroMoscowMapJson = new MetroMoscowMapJson();
//        metroMoscowMapJson.getStationsPerLine().forEach((station, line) -> System.out.println(station + "-" + line));
//        System.out.println(metroMoscowMapJson.getMainObject());
    }
}
