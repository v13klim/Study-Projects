import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable{
    private File[] files;
    private int targetSize;
    private String dstFolder;
    private long start;

    public ImageResizer(File[] files, int targetSize, String dstFolder, long start) {

        this.files = files;
        this.targetSize = targetSize;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        System.out.println(
                Thread.currentThread().getName() + " запущен " );
        try {
            for (File file : files) {
                BufferedImage originalImage = ImageIO.read(file);

                if (originalImage == null) {
                    continue;
                }

                BufferedImage resizedImage = Scalr.resize(originalImage, targetSize);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(resizedImage, "jpg", newFile);
                originalImage.flush();
                resizedImage.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(
                Thread.currentThread().getName() + " завершен -  время: " + (System.currentTimeMillis() - start));
    }


}
