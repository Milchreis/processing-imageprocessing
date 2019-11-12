package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

import static processing.core.PApplet.constrain;
import static processing.core.PApplet.map;

/**
 * @author milchreis
 */
public class Matte {

    public static PImage apply(PImage image, int matteIntensity) {
    	return apply(image, matteIntensity, 12, -0.02f);
	}

    public static PImage apply(PImage image, int matteIntensity, int contrastIntensity) {
        return apply(image, matteIntensity, contrastIntensity, -0.02f);
    }

    public static PImage apply(PImage image, int matteIntensity, int contrastIntensity, float saturationOffset) {

        int offset = constrain(matteIntensity, 0, 255);
        contrastIntensity = constrain(contrastIntensity, 0, 255);
        saturationOffset = constrain(saturationOffset, -0.5f, 0.5f);

        PImage output = image.copy();

        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {

                int[] rgb = Tools.getColors(image, x, y);
                float[] hsb = Tools.rgbToHsb(image.get(x, y));

                float strength = constrain((1.0f - (3 * hsb[2])), 0.0f, 1.0f);

                // Raise the dark pixel
                rgb[0] = (int) constrain(rgb[0] + (strength * offset), 0, 255);
                rgb[1] = (int) constrain(rgb[1] + (strength * offset), 0, 255);
                rgb[2] = (int) constrain(rgb[2] + (strength * offset), 0, 255);

                // Raise contrast
                Contrast.contrastPixel(rgb, Contrast.getContrasCorrectionFactor(contrastIntensity));

                // Lower saturation
                hsb = Tools.rgbToHsb(rgb);
                hsb[1] = constrain(hsb[1] + saturationOffset, 0.0f, 1.0f);

                Tools.set(output, x, y, Tools.hsbToRgb(hsb));
            }
        }

        return output;
    }

}
