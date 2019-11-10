package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

import static processing.core.PApplet.constrain;

public class Saturation {

    public static PImage apply(PImage image, float intensity) {

        intensity = constrain(intensity, 0.0f, 10.0f);

        if (intensity == 0) {
            return image;
        }

        PImage out = image.copy();

        for (int y = 0; y < image.width; y++) {
            for (int x = 0; x < image.height; x++) {

                int rgb = image.get(x, y);
                float[] hsb = Tools.rgbToHsb(rgb);

                hsb[1] = constrain(hsb[1] * intensity, 0.0f, 1.0f);

                out.set(x, y, Tools.hsbToRgb(hsb));
            }
        }

        return out;
    }

}
