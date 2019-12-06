package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

public class Flip {
	
	public static PImage apply(PImage image, boolean horizonal, boolean vertical) {
		
		if(image == null || image.width == 0 || image.height == 0)
			return image;
		
		PImage output = Tools.createBlankImageLike(image);
		
		for(int x=0; x < image.width; x++) {
			for(int y = 0; y < image.height; y++) {
				
				int color = image.get(x, y);
				
				int newx = horizonal ? (image.width - x) - 1 : x;
				int newy = vertical ?( image.height - y ) - 1: y;
				
				output.set(newx, newy, color);
			}
		}
		
		return output;
	}
}
