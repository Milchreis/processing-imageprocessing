package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

import static processing.core.PApplet.constrain;

public class Comparison {

    public static float howDifferent(PImage img1, PImage img2) {

        if(img1.width != img2.width || img1.height != img2.height)
            throw new RuntimeException("Images are not in the same dimension");

        long diff = 0L;

        for (int y = 0; y < img1.width; y++) {
            for (int x = 0; x < img1.height; x++) {

                int[] rgb1 = Tools.getRGB(img1.get(x, y));
                int[] rgb2 = Tools.getRGB(img2.get(x, y));

                for(int i=0; i<3; i++) {
                    diff += Math.abs(rgb1[i] - rgb2[i]);
                }
            }
        }

        float totalPixels = img1.width * img1.height * 3;
        float avgDifferentPixels = diff / totalPixels;
        float percentage = (avgDifferentPixels / 255.0f);
        return percentage;
    }

    public static PImage calculateDifferenceImage(PImage img1, PImage img2) {

        if(img1.width != img2.width || img1.height != img2.height)
            throw new RuntimeException("Images are not in the same dimension");

        PImage output = Tools.createBlankImageLike(img1);

        for (int y = 0; y < img1.width; y++) {
            for (int x = 0; x < img1.height; x++) {

                int[] rgb1 = Tools.getRGB(img1.get(x, y));
                int[] rgb2 = Tools.getRGB(img2.get(x, y));

                int[] pixel = new int[] {
                        constrain(Math.abs(rgb1[0] - rgb2[0]), 0, 255),
                        constrain(Math.abs(rgb1[1] - rgb2[1]), 0, 255),
                        constrain(Math.abs(rgb1[2] - rgb2[2]), 0, 255)
                };

                output.set(x, y, Tools.getRGB(pixel));
            }
        }

        return output;
    }
}
