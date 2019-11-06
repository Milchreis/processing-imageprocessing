package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

/**
 * @author milchreis
 */
public class Shadows {


    /**
     * Changes the dark areas in the given image.
     *
     * @param image     expects an image
     * @param intensity expects an intensity between -1.0 and 1.0
     * @return a new image with changed shadows
     */
    public static PImage apply(PImage image, float intensity) {

        if (intensity == 0) {
            return image;
        }

        intensity = intensity > 1.0f ? 1.0f : intensity;
        intensity = intensity < -1.0f ? -1.0f : intensity;

        PImage output = Tools.createBlankImageLike(image);

        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {

                int color = image.get(x, y);
                float[] hsb = Tools.rgbToHsb(color);

                // Invert the brightness value and increase it with the intensity
                hsb[2] += (1 - hsb[2]) * intensity;

                // Constraint
                hsb[2] = hsb[2] > 1.0f ? 1.0f : hsb[2];
                hsb[2] = hsb[2] < 0.0f ? 0.0f : hsb[2];

                output.set(x, y, Tools.hsbToRgb(hsb));
            }
        }

        return output;
    }
}
