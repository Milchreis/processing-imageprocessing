package milchreis.imageprocessing;

import processing.core.PImage;

import static milchreis.imageprocessing.utils.Tools.createBlankImageLike;
import static milchreis.imageprocessing.utils.Tools.getRGB;
import static processing.core.PApplet.map;

/**
 * A simple implementation of a sabattier effect.
 *
 * @author milchreis
 */
public class Sabattier {

    public static PImage apply(PImage image) {
        return apply(image, 0.2f, 2);
    }

    public static PImage apply(PImage image, float limit) {
        return apply(image, limit, 2);
    }

    public static PImage applyRed(PImage image, float limit) {
        return apply(image, limit, 0);
    }

    public static PImage applyGreen(PImage image, float limit) {
        return apply(image, limit, 1);
    }

    public static PImage applyBlue(PImage image, float limit) {
        return apply(image, limit, 2);
    }

    private static PImage apply(PImage image, float limit, int channel) {

        if (image == null || image.width == 0)
            return image;

        float fixedLimit = Math.min(Math.max(0.f, limit), 1.0f);
        int fixedChannel = Math.min(Math.max(0, channel), 2);

        PImage output = createBlankImageLike(image);

        for (int i = 0; i < image.pixels.length; i++) {

            int pixel = image.pixels[i];
            int[] rgb = getRGB(pixel);

            float redIntensity = rgb[0] / 255.0f;
            float greenIntensity = rgb[1] / 255.0f;
            float blueIntensity = rgb[2] / 255.0f;

            int color;

            if (fixedChannel == 0)
                color = (int) (255 * sabbatier(redIntensity, fixedLimit));
            else if (fixedChannel == 1)
                color = (int) (255 * sabbatier(greenIntensity, fixedLimit));
            else
                color = (int) (255 * sabbatier(blueIntensity, fixedLimit));

            output.pixels[i] = getRGB(color, color, color);
        }

        return output;
    }

    private static float sabbatier(float channelIntensity, float limit) {
        float r;
        if (channelIntensity < limit)
            r = map(channelIntensity, 0, limit, 1, 0);
        else
            r = map(channelIntensity, limit, 1, 0, 1);
        return r;
    }

}
