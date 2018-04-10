package milchreis.imageprocessing;

import processing.core.PImage;

public class Intensity {

	public static PImage apply(PImage image, float intensity) {
		
		PImage bw = Grayscale.apply(Sharpen.apply(image));
		PImage out = image.copy();
		
		out.blend(bw, 0, 0, image.width, image.height, 0, 0, image.width, image.height, PImage.SCREEN);
		
		return bw;
	}
	
}
