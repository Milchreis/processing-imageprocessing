package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

public class PaletteMapping {

    public static PImage apply(PImage image, int... colors) {

        if (image == null || colors == null || colors.length == 0)
            return image;

        int range = 256 / colors.length;
        LUT lutForPalette = new LUT();

        for(int colorIndex = 0; colorIndex < colors.length; colorIndex++) {

            int[] rgb = Tools.getRGB(colors[colorIndex]);

            for (int i = colorIndex*range; i < (colorIndex+1)*range; i++) {
                lutForPalette.lutR[i] = rgb[0];
                lutForPalette.lutG[i] = rgb[1];
                lutForPalette.lutB[i] = rgb[2];
            }
        }

        return LUT.apply(Grayscale.apply(image), lutForPalette);
    }

}
