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
public class Threshold {

	public static PImage apply(PImage _image, double threshold) {
		
		int p, r;
		
		PImage imageOutput = Tools.createImage(_image.width, _image.height, PConstants.RGB, _image);
		
		for (int i = 0; i < _image.width; i++) {
			for (int j = 0; j < _image.height; j++) {
				
				// Get pixels
				r = _image.get(i, j);
				r = ((r >> 16) & 0xff);
				
				if (r > threshold) {
					p = 255;
				} else {
					p = 0;
				}
				p = (p << 16) | (p << 8) | (p);
				imageOutput.set(i, j, p);
			}
		}
		
		return imageOutput;
	}

    public static PImage apply(PImage _image) {
    	double threshold = otsuTreshold(_image);
    	return apply(_image, threshold);
    }

    public static int otsuTreshold(PImage _image) {
        int _histogram[] = Histogram.histogtam(_image);

        int total = _image.width * _image.height;
        float sum = 0;
        for (int i = 0; i < 256; i++) {
            sum += i * _histogram[i];
        }
        float sum_bg = 0;
        int wight_bg = 0, wight_fg = 0;

        float varMax = 0;
        int threshold = 0;

        for (int i = 0; i < 256; i++) {
            wight_bg += _histogram[i];
            if (wight_bg == 0) {
                continue;
            }
            wight_fg = total - wight_bg;

            if (wight_fg == 0) {
                break;
            }

            sum_bg += (float) (i * _histogram[i]);
            float mean_bg = sum_bg / wight_bg;
            float mean_fg = (sum - sum_bg) / wight_fg;
            float varBetween = (float) wight_bg * (float) wight_fg * (mean_bg - mean_fg) * (mean_bg - mean_fg);
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }
}
