package milchreis.imageprocessing;

import processing.core.PConstants;
import processing.core.PImage;

/**
 * Source by https://github.com/avatarr/java-image-processing-algorithm
 * Conversion to working with processing by https://github.com/milchreis/
 * 
 * @author pratchaya
 * @author milchreis
 */
public class AutoBalance {

    public static int[] balancing(PImage _image) {
        int _histogram[] = Histogram.histogtam(_image);
        int _factor[] = new int[256];
        _factor = new Tools().getArray(_factor, 0);
        int sum = 0;
        float scale = (float) (255.0 / (_image.width * _image.height));

        for (int i = 0; i < _factor.length; i++) {
            sum += _histogram[i];
            int value =
                    (int) (sum * scale);
            if (value > 255) {
                _factor[i] = 255;
            } else {
                _factor[i] = value;
            }
        }
        return _factor;
    }

    /**
     * 
     * @param _image
     * @return
     */
    public static PImage apply(PImage _image) {
        int new_Histogram[] = balancing(_image);
        
        PImage output = Tools.createImage(_image.width, _image.height, PConstants.RGB, _image);
        int r, g, b;
        
        for (int i = 0; i < _image.width; i++) {
            for (int j = 0; j < _image.height; j++) {
            	
                int p = RGB.getRGBW(_image, i, j);
                r = (p >> 16) & 0xff;
                g = (p >> 8) & 0xff;
                b = (p & 0xff);

                r = new_Histogram[r];
                g = new_Histogram[g];
                b = new_Histogram[b];

                output.set(i, j, (r << 16) | (g << 8) | (b));
            }
        }

        return output;

    }
}
