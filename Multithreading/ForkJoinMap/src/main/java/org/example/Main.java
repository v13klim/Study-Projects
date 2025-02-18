package org.example;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String url = "https://skillbox.ru/";
        String pathToSiteMapFile = "data/siteMap.txt";
        SiteMap siteMap = new SiteMap(url);
        SiteMapRecursiveAction task = new SiteMapRecursiveAction(siteMap);
        new ForkJoinPool(Runtime.getRuntime().availableProcessors()).invoke(task);

        try {
            FileOutputStream stream = new FileOutputStream(pathToSiteMapFile);
            String siteMapFile = createSiteMapString(siteMap, 0);
            stream.write(siteMapFile.getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createSiteMapString(SiteMap siteMap, int tabulationSize) {
        String tab = String.join("", Collections.nCopies(tabulationSize, "\t"));
        StringBuilder result = new StringBuilder(tab + siteMap.getUrl());
        siteMap.getSiteMapChildrens().forEach(child -> result.append("\n")
                .append(createSiteMapString(child, tabulationSize + 1)));
        return result.toString();
    }
}