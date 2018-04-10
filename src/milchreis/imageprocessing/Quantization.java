package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

/**
 * 
 * @author milchreis
 */
public class Quantization {

    /**
     * Quantize the color range to a defined number of shades.
     * 
     * @param image		expects the image
     * @param shades	expects the number of shades per color channel
     * @return		
     */
    public static PImage apply(PImage image, int shades) {
        
        PImage output = Tools.createBlankImageLike(image);
        int r, g, b;
        
        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {
            	
                int pixel = image.get(x, y);
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = (pixel & 0xff);
                
                r = (int) (Math.round(shades * r / 255.0) * (255.0/shades));
                g = (int) (Math.round(shades * g / 255.0) * (255.0/shades));
                b = (int) (Math.round(shades * b / 255.0) * (255.0/shades));

                output.set(x, y, (r << 16) | (g << 8) | (b));
            }
        }

        return output;
    }
    
    public static PImage getError(PImage image, int shades) {
    	
    	PImage output = Tools.createBlankImageLike(image);
        int r, g, b;
        
        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {
            	
                int pixel = image.get(x, y);
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = (pixel & 0xff);
                
                int nr = (int) (Math.round(shades * r / 255.0) * (255.0/shades));
                int ng = (int) (Math.round(shades * g / 255.0) * (255.0/shades));
                int nb = (int) (Math.round(shades * b / 255.0) * (255.0/shades));

                output.set(x, y, (r - nr << 16) | (g - ng << 8) | (b - nb));
            }
        }

        return output;
    }
    
}
