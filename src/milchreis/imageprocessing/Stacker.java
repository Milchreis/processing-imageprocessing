package milchreis.imageprocessing;

import java.util.Arrays;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * The Stacker takes a bunch of images and grabs the median pixel of each images.
 * This technique could remove movable objects in pictures with the same scene.
 * 
 * More: https://petapixel.com/2013/05/29/a-look-at-reducing-noise-in-photographs-using-median-blending/
 * 
 * @author milchreis
 */
public class Stacker {

	public enum ALGORITHM {
		MEDIAN, AVERAGE;
	}
	
	public static PImage apply(PImage... images) {
		return apply(ALGORITHM.MEDIAN, images);
	}
	
	/**
	 *
	 * @param images	expects a list of images with the same size
	 * @return			a new image with the median pixel of each image 
	 */
	public static PImage apply(ALGORITHM algorithm, PImage... images) {

		if(images == null || images.length == 0)
			return null;
		
		int width = images[0].width;
		int height = images[0].height;
		
		PImage output = Tools.createImage(width, height, PConstants.RGB, images[0]);

		// Array for saving the current pixel from each image
		int[] pixels = new int[images.length];
		
		// Each pixel
		for(int x=0; x < width; x++) {
			for(int y=0; y < height; y++) {
				
				int sumR = 0, sumG = 0, sumB = 0;
				
				// Get pixel from each image
				for(int i=0; i < images.length; i++) {
					
					if(algorithm == ALGORITHM.MEDIAN) {
						pixels[i] = images[i].get(x, y);
					}
					
					if(algorithm == ALGORITHM.AVERAGE) {
						int pixel = images[i].get(x, y);
						sumR += (pixel >> 16) & 0xFF;
						sumG += (pixel >> 8) & 0xFF;
						sumB += (pixel) & 0xFF;
					}
				}
				
				int rgb = 0;
				
				if(algorithm == ALGORITHM.MEDIAN) {
					
					// Sort pixel values
					Arrays.sort(pixels);
					// Choose the median value
					rgb = pixels[pixels.length / 2];
				}
				
				if(algorithm == ALGORITHM.AVERAGE) {
					rgb = 255 << 24 | (sumR/images.length) << 16 | (sumG/images.length) << 8 | (sumB/images.length);
				}
				
				output.set(x, y, rgb);
			}
		}
				
		return output;
	}
}
