package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import static processing.core.PApplet.*;
import static processing.core.PConstants.TWO_PI;

public class Strokes {

    public static PImage apply(PImage img, int gridSize) {
        return apply(img, gridSize, null, 4, 1, 220, null, false, 255);
    }

    public static PImage apply(PImage img, int gridSize, float degree) {
        return apply(img, gridSize, null, 4, 1, 220, degree, false, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength) {
        return apply(img, gridSize, lineLength, 4, 1, 220, false, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength, float degree) {
        return apply(img, gridSize, lineLength, 4, 1, 220, degree, false, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength, int linesPerPixel) {
        return apply(img, gridSize, lineLength, linesPerPixel, 1, 220, false, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength, int linesPerPixel, float degree) {
        return apply(img, gridSize, lineLength, linesPerPixel, 1, 220, degree, false, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength, int linesPerPixel, int lineWeight, int lineAlpha) {
        return apply(img, gridSize, lineLength, linesPerPixel, lineWeight, lineAlpha, false, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength, int linesPerPixel, int lineWeight, int lineAlpha, float degree) {
        return apply(img, gridSize, lineLength, linesPerPixel, lineWeight, lineAlpha, degree, false, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength, int linesPerPixel, int lineWeight, int lineAlpha, boolean inColor) {
        return apply(img, gridSize, lineLength, linesPerPixel, lineWeight, lineAlpha, inColor, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength, int linesPerPixel, int lineWeight, int lineAlpha, float degree, boolean inColor) {
        return apply(img, gridSize, lineLength, linesPerPixel, lineWeight, lineAlpha, degree, inColor, 255);
    }

    public static PImage apply(PImage img, int gridSize, int lineLength, int linesPerPixel, int lineWeight, int lineAlpha, boolean inColor, int backgroundColor) {
        return apply(img, gridSize, lineLength, linesPerPixel, lineWeight, lineAlpha, null, inColor, backgroundColor);
    }

    public static PImage apply(PImage img, int gridSize, Integer lineLength, int linesPerPixel, int lineWeight, int lineAlpha, Float degree, boolean inColor, int backgroundColor) {

        gridSize = constrain(gridSize, 1, Integer.MAX_VALUE);
        linesPerPixel = constrain(linesPerPixel, 0, Integer.MAX_VALUE);
        lineWeight = constrain(lineWeight, 0, Integer.MAX_VALUE);
        lineAlpha = constrain(lineAlpha, 0, 255);
        backgroundColor = constrain(backgroundColor, 0, 255);

        PGraphics canvas = img.parent.createGraphics(img.width, img.height);
        canvas.beginDraw();
        canvas.background(backgroundColor);

        for (int x = 0; x < img.width; x += gridSize) {
            for (int y = 0; y < img.height; y += gridSize) {

                int pixel = Tools.getGrey(img.get(x, y));
                int numberOfStrokes = (int) map(pixel, 255, 0, 0, linesPerPixel);

                if (inColor) {
                    canvas.stroke(img.get(x, y), lineAlpha);
                } else {
                    canvas.stroke(0, 0, 0, lineAlpha);
                }

                canvas.strokeWeight(lineWeight);

                for (int i = 0; i < numberOfStrokes; i++) {
                    canvas.pushMatrix();
                    canvas.translate(x + img.parent.random(-gridSize / 2), y + img.parent.random(gridSize / 2));

                    if (degree == null) {
                        canvas.rotate(img.parent.random(0, TWO_PI));
                    } else {
                        canvas.rotate(radians(degree));
                    }

                    if(lineLength == null) {
                        lineLength = (int) map(pixel, 0, 255, 0, gridSize);
                    }

                    canvas.line(-lineLength, 0, lineLength, 0);
                    canvas.popMatrix();
                }
            }
        }
        canvas.endDraw();

        return canvas;
    }

}
