package milchreis.imageprocessing;

import processing.core.PImage;

/**
 * 
 * @author milchreis
 */
public class Sobel {

	private static final double[][] SOBEL_X = {
			{+1, 0, -1},
			{+2, 0, -2},
			{+1, 0, -1},
	};
	
	private static final double[][] SOBEL_Y = {
			{+1, +2, +1},
			{ 0,  0,  0},
			{-1, -2, -1},
	};

	
	/**
	 * Sharps an image by a default convolution kernel. 
	 * @param image		expects an image
	 * @return			returns a sharped new image object
	 */
	public static PImage apply(PImage image) {
		
		PImage p = Grayscale.apply(image);
		PImage px = Convolution.apply(p, SOBEL_X);
		PImage py = Convolution.apply(p, SOBEL_Y);
		px.blend(py, 0, 0, image.width, image.height, 0, 0, image.width, image.height, PImage.MULTIPLY);
		return px;
	}
}
