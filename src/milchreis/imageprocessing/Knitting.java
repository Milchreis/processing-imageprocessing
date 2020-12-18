package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PGraphics;
import processing.core.PImage;

import static processing.core.PConstants.ROUND;

/**
 * @author milchreis
 */
public class Knitting {

    public static PImage apply(PImage image, int size) {
        return apply(image, size, 0.5f, 0, 0, true);
    }

    public static PImage apply(PImage image, int size, float threshold) {
        return apply(image, size, threshold, 0, 0, true);
    }

    public static PImage apply(PImage image, int size, int foreground, int background) {
        return apply(image, size, 0.5f, foreground, background, false);
    }

    public static PImage apply(PImage image, int size, float threshold, int foreground, int background) {
        return apply(image, size, threshold, foreground, background, false);
    }

    private static PImage apply(PImage image, int size, float threshold, int foreground, int background, boolean inColor) {

        if (image.width <= 0 || image.height <= 0 || size <= 0)
            return image;

        PGraphics canvas = image.parent.createGraphics(image.width, image.width);
        canvas.beginDraw();

        canvas.background(background);
        canvas.stroke(foreground);

        canvas.strokeCap(ROUND);
        canvas.strokeWeight(size * 0.5f);

        int gap = (int) (size * 0.7f);

        for (int x = 0; x < canvas.width; x += size + gap) {
            for (int y = 0; y < canvas.height; y += size + gap) {

                int color = image.get(x + size / 2, y + size / 2);
                float sum = Tools.getGrey(color) / 255.0f;

                if (inColor) {
                    canvas.stroke(color);
                    canvas.line(x, y, x + size * 0.3f, y + size);
                    canvas.line(x + size, y, x + size * 0.7f, y + size);

                } else {
                    canvas.stroke(foreground);
                    if (sum > threshold) {
                        canvas.line(x, y, x + size * 0.3f, y + size);
                        canvas.line(x + size, y, x + size * 0.7f, y + size);
                    }
                }
            }
        }

        canvas.endDraw();
        return canvas.get();
    }

}
