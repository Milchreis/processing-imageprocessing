package milchreis.imageprocessing;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Special thanks goes to Joseph HENRY:
 * https://discourse.processing.org/t/image-to-sine-waves/25938/2
 */
public class SineWave {

    public static PImage apply(PImage image) {
        return apply(image, 3, 1.5f, 255, 0);
    }

    public static PImage apply(PImage image, float height, float strokeWeight, int backgroundColor, int waveColor) {

        if (image.width <= 0 || image.height <= 0)
            return image;

        int sineHeight = 10;
        float sineIncr = 0.1f;

        PGraphics canvas = image.parent.createGraphics(image.width, image.height);
        canvas.beginDraw();
        canvas.background(backgroundColor);
        canvas.noFill();
        canvas.stroke(waveColor);
        canvas.strokeWeight(strokeWeight);

        final float rowHeight = sineHeight + height;
        for (int y = 0; y < canvas.height; y += rowHeight) {
            canvas.beginShape();
            // We start with a position of 0 for the sine function
            // Think of it as the x on the graphs
            float sinePos = 0;

            if(y >= canvas.height) {
                y = canvas.height - 1;
            }

            // Go until it fills the width
            while (sinePos < canvas.width) {
                // We have multiple points per pixel
                // So we have to pick the closest point for a x coordinate by rounding it
                int closestPixelLoc = PApplet.round(sinePos) + y * image.width;

                // Compute the brightness of that pixel in [0, 1]
                float br = (float) (canvas.brightness(image.pixels[closestPixelLoc]) / 255.0);

                // The sine wave is oscillating above and under the pixel height
                canvas.curveVertex(sinePos, y + PApplet.sin(sinePos) * (sineHeight / 2.0f - height));

                // We increase the x position of the sine wave
                // We use an exponential function to vary faster when the pixel is brighter
                sinePos += PApplet.exp(br * 4) * sineIncr;
            }
            canvas.endShape();
        }

        canvas.endDraw();
        return canvas.get();
    }

}
