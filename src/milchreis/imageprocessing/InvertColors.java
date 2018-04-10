package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

public class InvertColors {
	
	public static PImage apply(PImage image, boolean red, boolean green, boolean blue) {
		
		if(image == null || image.width == 0 || image.height == 0)
			return image;
		
		PImage output = Tools.createBlankImageLike(image);
		
		for(int x=0; x < image.width; x++) {
			for(int y = 0; y < image.height; y++) {
				
				int[] rgb = Tools.getColors(image, x, y);
				
				if(red) {
					rgb[0] = 255 - rgb[0]; 
				}
				
				if(green) {
					rgb[1] = 255 - rgb[1]; 
				}
				
				if(blue) {
					rgb[2] = 255 - rgb[2]; 
				}
				
				Tools.set(output, x, y, rgb);
			}
		}
		
		return output;
	}
}
