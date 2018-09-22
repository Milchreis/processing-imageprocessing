package milchreis.imageprocessing;

import processing.core.PImage;

/**
 * A simple implementation of a pixelation effect.
 * 
 * @author milchreis
 */
public class Pixelation {

	/**
	 * Increases the size of the pixel and makes a image unrecognizable.
	 * 
	 * @param image			expects an image
	 * @param pixelsize		expects the size for a pixel
	 * @return				a new image
	 */
	public static PImage apply(PImage image, int pixelsize) {
		return apply(image, pixelsize, 0, 0, image.width, image.height);
	}
	
	/**
	 * Increases the size of the pixel in a defined area and makes a image unrecognizable.
	 * 
	 * @param image			expects an image
	 * @param pixelsize		expects the size for a pixel
	 * @param subx			expects the x-position of the pixelized area
	 * @param suby			expects the y-position of the pixelized area
	 * @param width			expects the width of the pixelized area
	 * @param height		expects the height of the pixelized area
	 * @return
	 */
	public static PImage apply(PImage image, int pixelsize, int subx, int suby, int width, int height) {
		
		if(pixelsize < 1 || pixelsize >= image.width)
			return image;
		
		PImage output = image.copy();
		
		int pwidth = image.width;
		int pheight = image.height;
		
		for (int y = suby; y < height; y += pixelsize) {
			for (int x = subx; x < width; x += pixelsize) {
				
				int pixel = image.get(x, y);
				
				for (int yp = y; (yp < y + pixelsize) && (yp < pheight); yp++) {
					for (int xp = x; (xp < x + pixelsize) && (xp < pwidth); xp++) {
						output.set(xp, yp, pixel);
					}
				}
			}
		}
		
		return output;
	}

}
