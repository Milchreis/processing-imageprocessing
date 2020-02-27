package milchreis.imageprocessing;

import processing.core.PImage;

public class RetroConsole {

    public static PImage applyGameboy(PImage image, int pixelsize) {

        if(pixelsize <= 0)
            throw new IllegalArgumentException("The second argument (pixelsize) has to be greater than zero.");

        if (image == null)
            return image;

        return PaletteMapping.apply(Pixelation.apply(image, pixelsize), 997391, 3170864, 9153551, 10206223);
    }

}
