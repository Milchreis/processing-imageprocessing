package milchreis.imageprocessing;

import processing.core.PConstants;
import processing.core.PImage;

/**
 * A simple implementation of a bloom like shader effect.
 * 
 * @author milchreis
 */
public class Bloom {

	/**
	 * Returns a new image with "glowing" highlights.
	 * 
	 * @param image			expects an image
	 * @return
	 */
	public static PImage apply(PImage image) {
		return apply(image, 100, 4);
	}
	
	/**
	 * Returns a new image with "glowing" highlights.
	 * 
	 * @param image			expects an image
	 * @param intensity		expects an intensity in range 0 to 255 (alpha for the blurred layer)
	 * @return
	 */
	public static PImage apply(PImage image, int intensity) {
		return apply(image, intensity, 4);
	}
	
	/**
	 * Returns a new image with "glowing" highlights.
	 * 
	 * @param image			expects an image
	 * @param intensity		expects an intensity in range 0 to 255 (alpha for the blurred layer)
	 * @param glow			expects a value incl. between 0 and 8 (strange of the blur)
	 * @return
	 */
	public static PImage apply(PImage image, int intensity, int glow) {
		
		if(glow < 0)
			glow = 0;
		
		if(glow > 8)
			glow = 8;
		
		PImage output = image.copy();
		
		// Blur the image
		PImage blurred = output.copy();
		blurred.filter(PConstants.BLUR, glow);

		blurred.loadPixels();
		for (int i = 0; i < blurred.pixels.length-1; i++) {
			
			int pixel = blurred.pixels[i];
			
			// right shift to separate channels
			int a = intensity;
			int r = (pixel >> 16) & 0xFF;
			int g = (pixel >> 8) & 0xFF;
			int b = (pixel) & 0xFF;
			
			int argb = a << 24 | r << 16 | g << 8 | b;
			
			blurred.pixels[i] = argb;
		}
		blurred.updatePixels();
		
		// Blend image with screen mode
		output.blend(blurred, 0, 0, image.width, image.height, 0, 0, image.width, image.height, PImage.SCREEN);
		
		return output;
	}
	
}
