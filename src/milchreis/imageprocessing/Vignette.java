package milchreis.imageprocessing;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * A simple implementation of a vignette effect
 *
 * @author milchreis
 */
public class Vignette {
	
	public static PImage apply(PImage image) {
		return apply(image, getMask(image, (int) (image.width * 0.5), 30, 0), 0.8f);
	}
	
	public static PImage apply(PImage image, float intensity, float width) {
		width = PApplet.map(width, 0.0f, 1.0f, 0.3f, 1.5f);
		int vignetteW = (int) (image.width * width);
		return apply(image, getMask(image, vignetteW, 30, 0), intensity);
	}
	
	public static PImage apply(PImage image, float intensity) {
		return apply(image, getMask(image, (int) (image.width * 0.5), 30, 0), intensity);
	}

	public static PImage getMask(PImage image, int radius, int blurStrange, int color) {

		PGraphics mask = image.parent.createGraphics(image.width, image.height);
		
		mask.beginDraw();
		mask.background(color);
		
		mask.ellipse(image.width/2, image.height/2, radius, radius);
		mask.filter(PConstants.BLUR, blurStrange);
		mask.endDraw();

		return mask.get();
	}
	
	public static PImage apply(PImage image, PImage mask, float intensity) {

		PGraphics output = image.parent.createGraphics(image.width, image.height);
		PImage copy = image.copy();
		copy.blend(mask, 0, 0, image.width, image.height, 0, 0, image.width, image.height, PImage.MULTIPLY);
		
		output.beginDraw();
		output.image(image, 0, 0);
		output.tint(255, PApplet.map(intensity, 0.0f, 1.0f, 0, 255));
		output.image(copy, 0, 0);
		output.endDraw();
		
		return output.get();
	}
	
	public static PImage apply(PImage image, int radius, int blurStrange, float intensity, int color) {
		PImage mask = getMask(image, radius, blurStrange, color);
		return apply(image, mask, intensity);
	}
}
