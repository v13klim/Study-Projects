import java.io.File;

public class SearchFiles {
    private final String folderPath = "data/data";
    private String JsonFilesAbsolutePath;
    private String CsvFilesAbsolutePath;

    public SearchFiles() {
        getJsonFiles(folderPath);
        getCsvFiles(folderPath);
    }




    private String getJsonFiles(String folderPath) {
        JsonFilesAbsolutePath = "";
        File folder = new File(folderPath);
        if (folder.isFile() && folder.getName().endsWith(".json")) {
            JsonFilesAbsolutePath += folder.getAbsolutePath() + "\n";
        }

        try {
            File[] files = folder.listFiles();
            for (File file : files) {
                JsonFilesAbsolutePath += getJsonFiles(file.getAbsolutePath());
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return JsonFilesAbsolutePath;
    }

    private String getCsvFiles(String folderPath) {
        CsvFilesAbsolutePath = "";
        File folder = new File(folderPath);
        if (folder.isFile() && folder.getName().endsWith(".csv")) {
            CsvFilesAbsolutePath += folder.getAbsolutePath() + "\n";
        }
        try {
            File[] files = folder.listFiles();
            for (File file : files) {
                CsvFilesAbsolutePath += getCsvFiles(file.getAbsolutePath());
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return CsvFilesAbsolutePath;
    }

    public String getJsonFilesAbsolutePath() {
        return JsonFilesAbsolutePath;
    }

    public String getCsvFilesAbsolutePath() {
        return CsvFilesAbsolutePath;
    }

}
