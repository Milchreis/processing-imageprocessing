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
public class Grayscale {

    public static PImage apply(PImage _image) {

        PImage imageOutput = Tools.createImage(_image.width, _image.height, PConstants.RGB, _image);

        for (int i = 0; i < _image.width; i++) {
            for (int j = 0; j < _image.height; j++) {
                int rgb;
                int p = _image.get(i, j);

                rgb = (int) ((((p >> 16) & 0xFF) * 0.2125) + (((p >> 8) & 0xFF) * 0.7154) + ((p & 0xFF) * 0.0721));
                rgb = (rgb << 16) | (rgb << 8) | (rgb);

                imageOutput.set(i, j, rgb);
            }
        }
        return imageOutput;
    }
}
