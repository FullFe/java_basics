import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread{

    private final File[] files;
    private final long time;
    private final String dstFolder;

    public ImageResizer(File[] files, long time, String dstFolder) {
        this.files = files;
        this.time = time;
        this.dstFolder = dstFolder;
    }
    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

//                int newWidth = 300;
//                int newHeight = (int) Math.round(
//                    image.getHeight() / (image.getWidth() / (double) newWidth)
//                );
//                BufferedImage newImage = new BufferedImage(
//                    newWidth, newHeight, BufferedImage.TYPE_INT_RGB
//                );
//
//                int widthStep = image.getWidth() / newWidth;
//                int heightStep = image.getHeight() / newHeight;
//
//                for (int x = 0; x < newWidth; x++) {
//                    for (int y = 0; y < newHeight; y++) {
//                        int rgb = image.getRGB(x * widthStep, y * heightStep);
//                        newImage.setRGB(x, y, rgb);
//                    }
//                }
                double targetHeight = (double) (300 * image.getHeight()) / image.getWidth();
                int tH = (int) Math.round(targetHeight);
                BufferedImage newImage = resizeImage(image, tH);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
            System.out.println(System.currentTimeMillis() - time);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static BufferedImage resizeImage(BufferedImage originalImage, int targetHeight) throws Exception {
        return Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 300, targetHeight, Scalr.OP_ANTIALIAS);
    }
}
