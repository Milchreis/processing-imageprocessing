package milchreis.imageprocessing;

import processing.core.PImage;

/**
 * Source by https://github.com/avatarr/java-image-processing-algorithm
 * Conversion to working with processing by https://github.com/milchreis/
 * 
 * @author pratchaya
 * @author milchreis
 */
public class Histogram {

    public static int[] histogtam(PImage _image) {
    	
        int interval[] = new int[256];
        for (int i = 0; i < _image.width; i++) {
            for (int j = 0; j < _image.height; j++) {
                int p = _image.get(i, j);
                int r = (p >> 16) & 0xff;
                interval[r]++;
            }

        }
        
        return interval;

    }

}
