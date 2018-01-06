package milchreis.imageprocessing;

import processing.core.PImage;

/**
 * 
 * @author milchreis
 */
public class Sharpen {

	private static final double[][] KERNEL = {
			{-1, -1, -1},
			{-1,  9, -1},
			{-1, -1, -1},
	};

	
	/**
	 * Sharps an image by a default convolution kernel. 
	 * @param image		expects an image
	 * @return			returns a sharped new image object
	 */
	public static PImage apply(PImage image) {
		return Convolution.apply(image, KERNEL);
	}

	/**
	 * Sharps an image by convolution and a specified intensity.
	 * The intensity should be positive number.
	 * 
	 * @param image		expects an image
	 * @param intensity	expects the intensity (0 = nothing)
	 * @return			returns a sharped new image object
	 */
	public static PImage apply(PImage image, double intensity) {
		
		double strange = -1*intensity;
		double[][] kernel = {
				{0, strange, 0},
				{strange,  1 + 4*Math.abs(strange), strange},
				{0, strange, 0},
		};
		
        return Convolution.apply(image, kernel);
    }
}
