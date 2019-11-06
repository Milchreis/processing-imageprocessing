package milchreis.imageprocessing;

import processing.core.PImage;

import static processing.core.PApplet.constrain;

public class Toning {

    public static PImage apply(PImage image, int color, float intensity) {

        PImage output = Tools.createBlankImageLike(image);
        int[] tone = Tools.getRGB(color);

        for (int y = 0; y < image.width; y++) {
            for (int x = 0; x < image.height; x++) {

                int[] rgb = Tools.getRGB(image.get(x, y));

                rgb[0] = (int)constrain( rgb[0] + ((float)tone[0] * intensity), 0, 255);
                rgb[1] = (int)constrain( rgb[1] + ((float)tone[1] * intensity), 0, 255);
                rgb[2] = (int)constrain( rgb[2] + ((float)tone[2] * intensity), 0, 255);

                output.set(x, y, Tools.getRGB(rgb));
            }
        }

        return output;
    }

}
