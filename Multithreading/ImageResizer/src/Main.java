import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        String srcFolder = "C:\\Users\\vladk\\Desktop\\src";
        String dstFolder = "C:\\Users\\vladk\\Desktop\\dst";
        File srcDir = new File(srcFolder);
        int targetSize = 600;

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        Runtime runtime = Runtime.getRuntime();

        int nrOfProcessors = runtime.availableProcessors();
        int middle = files.length / nrOfProcessors;

        for (int i = 0; i < 10; i++) {
            File[] file1 = new File[middle];
            System.arraycopy(files, middle * i, file1, 0, middle);
            ImageResizer imageResizer = new ImageResizer(file1, targetSize, dstFolder, start);
            new Thread(imageResizer).start();

        }


    }
}
