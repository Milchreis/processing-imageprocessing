package milchreis.imageprocessing;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * 
 * @author milchreis
 */
public class TiltShift {

	public static PImage createMask(PImage image, int blurIntensity, boolean horizontal, int position, int width) {
		
		PGraphics mask = image.parent.createGraphics(image.width, image.height);
		PApplet main = image.parent;
		
		int c1 = 255;
		int c2 = 0;
		
		mask.beginDraw();
		mask.background(0);
		
		for (int i = 0; i <= width; i++) {
			float inter = PApplet.map(i, 0, width, 0, 1);
			int c = main.lerpColor(c1, c2, inter);
			mask.stroke(c);

			if (horizontal) { 
				// Top to bottom gradient
				mask.line(0, position + i, image.width, position + i);
				mask.line(0, position - i, image.width, position - i);
				
			} else { 
				// Left to right gradient
				mask.line(position + i, 0, position + i, image.height);
				mask.line(position - i, 0, position - i, image.height);
			}
		}
		
		mask.endDraw();
		
		return mask.get();
	}
	    
    public static PImage apply(PImage image, int blurIntensity, boolean horizontal, int position, int width) {
    	
    	if(image.width <= 0 || image.height <= 0 || width <= 0 || blurIntensity <= 0)
    		return image;

    	// Create blur
    	PImage blurred = image.copy();
    	blurred.filter(PConstants.BLUR, blurIntensity);
    	
    	// Create mask
    	PImage mask = createMask(image, blurIntensity, horizontal, position, width);
    	
    	// Apply mask
    	PImage sharp = image.copy();
    	sharp.mask(mask);
    	
    	PGraphics output = image.parent.createGraphics(image.width, image.height);
    	output.beginDraw();
    	output.image(blurred, 0, 0);
    	output.image(sharp, 0, 0);
    	output.endDraw();

    	return output.get();
    }
}

