package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author milchreis
 */
public class ColorShift {
	
	
	public static PImage apply(PImage image, int shift) {
		PImage output = image.copy();
        
        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {
            	
            	int pixel = image.get(x, y);
            	float[] hsb = Tools.rgbToHsb(pixel);

        		hsb[0] += shift;
        		hsb[0] = PApplet.constrain(hsb[0], 0, 360);
            	
            	pixel = Tools.hsbToRgb(hsb[0], hsb[1], hsb[2]);
                output.set(x, y, pixel);
            }
        }

        return output;
	}
	
	public static PImage apply(PImage image, int hue, int offset, int shift) {
		return applyHue(image, hue, offset, shift);
	}
	
    public static PImage applyHue(PImage image, int hue, int offset, int shift) {
        
        PImage output = image.copy();
        
        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {
            	
            	int pixel = image.get(x, y);
            	float[] hsb = Tools.rgbToHsb(pixel);

            	if(Tools.in(hue, hsb[0]-offset, hsb[0]+offset)) {
            		hsb[0] += shift;
            		hsb[0] = PApplet.constrain(hsb[0], 0, 360);
            	}
            	
            	pixel = Tools.hsbToRgb(hsb[0], hsb[1], hsb[2]);
                output.set(x, y, pixel);
            }
        }

        return output;
    }
    
    public static PImage applySaturation(PImage image, int hue, int offset, float shift) {
    	
    	PImage output = image.copy();
    	
    	for (int x = 0; x < image.width; x++) {
    		for (int y = 0; y < image.height; y++) {
    			
    			int pixel = image.get(x, y);
    			float[] hsb = Tools.rgbToHsb(pixel);
    			
    			if(Tools.in(hue, hsb[0]-offset, hsb[0]+offset)) {
    				hsb[1] += shift;
    				hsb[1] = PApplet.constrain(hsb[1], 0, 1);
    			}
    			
    			pixel = Tools.hsbToRgb(hsb[0], hsb[1], hsb[2]);
    			output.set(x, y, pixel);
    		}
    	}
    	
    	return output;
    }
    
    public static PImage applyBrightness(PImage image, int hue, int offset, float shift) {
    	
    	PImage output = image.copy();
    	
    	for (int x = 0; x < image.width; x++) {
    		for (int y = 0; y < image.height; y++) {
    			
    			int pixel = image.get(x, y);
    			float[] hsb = Tools.rgbToHsb(pixel);
    			
    			if(Tools.in(hue, hsb[0]-offset, hsb[0]+offset)) {
    				hsb[2] += shift;
    				hsb[2] = PApplet.constrain(hsb[2], 0, 1);
    			}
    			
    			pixel = Tools.hsbToRgb(hsb[0], hsb[1], hsb[2]);
    			output.set(x, y, pixel);
    		}
    	}
    	
    	return output;
    }
    
}
