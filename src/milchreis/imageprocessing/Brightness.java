package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.stream.IntStream;

/**
 * @author milchreis
 */
public class Brightness {

    public static PImage apply(PImage image, int intensity) {

        PImage output = image.copy();

        IntStream.range(1, image.width * image.height)
                .parallel()
                .forEach(i -> {
                    int x = i % image.width;
                    int y = i / image.height;


                    int[] rgb = Tools.getColors(image, x, y);

                    rgb[0] += intensity;
                    rgb[1] += intensity;
                    rgb[2] += intensity;

                    rgb[0] = PApplet.constrain(rgb[0], 0, 255);
                    rgb[1] = PApplet.constrain(rgb[1], 0, 255);
                    rgb[2] = PApplet.constrain(rgb[2], 0, 255);

                    Tools.set(output, x, y, rgb[0], rgb[1], rgb[2]);
                });

        return output;
    }

}
