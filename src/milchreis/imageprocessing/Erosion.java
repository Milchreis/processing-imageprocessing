package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * Source by https://github.com/avatarr/java-image-processing-algorithm
 * Conversion to working with processing by https://github.com/milchreis/
 * 
 * @author pratchaya
 * @author milchreis
 */
public class Erosion {

    private static int minPixel(PImage _image, int _w, int _h, int r) {
        int minR = 255;
        int minG = 255;
        int minB = 255;
        int radiusPow = r * r;
        int _r = r/2;
        for (int i = -_r; i < _r; i++) {
            for (int j = -_r; j < _r; j++) {
                if (i * i + j * j < radiusPow) {
                    int c = _image.get(_w + i, _h + j);
                    minR = Math.min(minR, (c >> 16) & 0xFF);
                    minG = Math.min(minG, (c >> 8) & 0xFF);
                    minB = Math.min(minB, c & 0xFF);
                } // end if
            } // end j
        } // end i

        return (minR << 16) | (minG << 8) | minB;
    }

    public static PImage apply(PImage _image, int _radius) {

        int width = _image.width;
        int height = _image.height;
        int radius = _radius;
        PImage image = Tools.createImage(_image.width, _image.height, PConstants.RGB, _image);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.set(i, j, minPixel(_image, i, j, radius));
            }
        }

        return image;
    }
}
