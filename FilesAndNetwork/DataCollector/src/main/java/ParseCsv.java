import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParseCsv {
    private List<String> csvStringList;
    private List<StationDate> dates;

    public ParseCsv() {
        parseCsv();
        filtredCsv();
    }

    public List<String> splitCsvFile() {
        List<String> lines;
        csvStringList = new ArrayList<>();
        SearchFiles filesSearch = new SearchFiles();
        String[] paths = filesSearch.getCsvFilesAbsolutePath().split("\n");
        for (String path : paths) {
            try {
                lines = Files.readAllLines(Paths.get(path));
                lines.remove(0);
                csvStringList.addAll(lines);
                } catch (IOException e) {
                e.getMessage();
                }
            }
        return csvStringList;
    }

    public void filtredCsv() {
        for (int i = 0; i < dates.size(); i ++) {
            String nameI = dates.get(i).getName();
            String dateI = dates.get(i).getDate();

            for (int j = 0; j <dates.size(); j++) {
                String nameJ = dates.get(j).getName();
                String dateJ = dates.get(j).getDate();

                if (nameI.equals(nameJ) && dateI.equals(dateJ)) {
                    dates.remove(j);
                }
            }
        }
    }

    private void parseCsv() {
        dates = new ArrayList<>();
        List<String> lines = splitCsvFile();
        for (String line : lines) {
            String[] tokens = line.split(",");
            if (tokens.length != 2) {
                System.out.println("Неверное значение из файла" + line);
            }
            dates.add(new StationDate(tokens[0], tokens[1]));
        }
    }

    public List<StationDate> getStationsDates() {
        return dates;
    }
}
