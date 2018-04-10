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
        
        output.loadPixels();
        for (int i = 0; i < _image.width; i++) {
            for (int j = 0; j < _image.height; j++) {
            	
            	int[] rgb = Tools.getColors(_image, i, j);

                r = new_Histogram[rgb[0]];
                g = new_Histogram[rgb[1]];
                b = new_Histogram[rgb[2]];

                output.set(i, j, (r << 16) | (g << 8) | (b));
            }
        }

        output.updatePixels();
        return output;

    }
}
