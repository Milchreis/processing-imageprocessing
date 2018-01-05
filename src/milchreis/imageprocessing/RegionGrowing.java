package milchreis.imageprocessing;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * 
 * @author nick
 */
public class RegionGrowing {

	public static PImage apply(PImage image, int seedX, int seedY, float distance) {

		PImage img = Tools.createImage(image.width, image.height, PConstants.RGB, image);
		grow(img, image, seedX, seedY, distance);
		return img;
	}

	private static void grow(PImage target, PImage original, int x, int y, float distance) {

		int originalPixel = original.get(x, y);
		int originalPixelR = (originalPixel >> 16) & 0xFF;
		int originalPixelG = (originalPixel >> 8) & 0xFF;
		int originalPixelB = (originalPixel) & 0xFF;

		// top
		if (y - 1 > 0) {
			checkPixel(target, original, x, y - 1, distance, originalPixelR, originalPixelG, originalPixelB);
		}

		// left
		if (x - 1 > 0) {
			checkPixel(target, original, x - 1, y, distance, originalPixelR, originalPixelG, originalPixelB);
		}

		// right
		if (x + 1 < target.width) {
			checkPixel(target, original, x + 1, y, distance, originalPixelR, originalPixelG, originalPixelB);
		}

		// bottom
		if (y + 1 < target.height) {
			checkPixel(target, original, x, y + 1, distance, originalPixelR, originalPixelG, originalPixelB);
		}
	}
	
	private static void checkPixel(PImage target, PImage original, int x, int y, float distance, int orgR, int orgG, int orgB) {
		
		int pixel = target.get(x, y);
		int r = (pixel >> 16) & 0xFF;
		int g = (pixel >> 8) & 0xFF;
		int b = (pixel) & 0xFF;
		
		float d = PApplet.dist(orgR, orgG, orgB, r, g, b); 
		
		if(d < PApplet.abs(distance)) {
			target.set(x,  y, 16777215);
			grow(target, original, x, y, distance);
		}
	}
}
