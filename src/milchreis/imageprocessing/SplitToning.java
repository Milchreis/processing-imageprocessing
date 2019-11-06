package milchreis.imageprocessing;

import processing.core.PImage;

import static processing.core.PApplet.constrain;

public class SplitToning {

    public static PImage apply(PImage image, int highlightColor, float highlightIntensity, int shadowsColor, float shadowsIntensity) {

        highlightIntensity = constrain(highlightIntensity, 0, 1);
        shadowsIntensity = constrain(shadowsIntensity, 0, 1);

        if(highlightIntensity == 0 && shadowsIntensity == 0)
            return image;

        PImage output = Tools.createBlankImageLike(image);

        int[] highlightTone = Tools.getRGB(highlightColor);
        int[] shadowTone = Tools.getRGB(shadowsColor);

        for (int y = 0; y < image.width; y++) {
            for (int x = 0; x < image.height; x++) {

                int color = image.get(x, y);
                int[] rgb = Tools.getRGB(color);
                float[] hsb = Tools.rgbToHsb(color);

                Toning.tonePixel(rgb, highlightTone, highlightIntensity * hsb[2]);
                Toning.tonePixel(rgb, shadowTone, shadowsIntensity * (1-hsb[2]));

                output.set(x, y, Tools.getRGB(rgb));
            }
        }

        return output;
    }

}
