import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ParseHtml {
    private String url = "https://skillbox-java.github.io/";
    private List<Line> lineList = new ArrayList<>();
    private List<Station> stationsList = new ArrayList<>();
    public ParseHtml() {

        try {
            /*
            Получение страницы html и запись в файл:
             */
            Document document = Jsoup.connect(url).get();
            PrintWriter writer = new PrintWriter("data/metro.html");
            writer.write(document.outerHtml());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        parseHtml();
    }

    private void parseHtml() {

        try {
            File file = new File("data/metro.html");
            Document doc = Jsoup.parse(file);
            Elements lines = doc.select("span.js-metro-line");

            for (Element element : lines) {
                lineList.add(new Line(element.text(), element.attr("data-line")));
            }

            Elements stationsPerLine = doc.getElementsByClass("js-metro-stations");
            for (Element element : stationsPerLine) {
                Elements stationsInfo = element.getElementsByClass("single-station");
                for (Element station : stationsInfo) {
                    Boolean hasConnection = station.select("span.t-icon-metroln").hasAttr("title");
                    stationsList.add(new Station(station.getElementsByClass("name").text(),
                            element.attr("data-line"), hasConnection));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
    public List<Line> getLines() {
        return lineList;
    }

    public List<Station> getStations() {
        return stationsList;
    }
}
