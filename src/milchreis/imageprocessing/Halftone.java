package milchreis.imageprocessing;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * 
 * @author milchreis
 */
public class Halftone {

	private static final double SQUARE_ROOT_2 = Math.sqrt(2);

	public static PImage apply(PImage image) {
		return apply(image, 100);
	}
	
	public static PImage apply(PImage image, int dotsize) {
		return apply(image, dotsize, 0, 255, 1, false);
	}
	
	public static PImage apply(PImage image, int dotsize, boolean grid) {
		return apply(image, dotsize, 0, 255, 1, grid);
	}
	
	public static PImage apply(PImage image, int dotsize, int foreground, int background) {
		return apply(image, dotsize, foreground, background, 1, false);
	}
	
	public static PImage apply(PImage image, int dotsize, int foreground, int background, boolean grid) {
		return apply(image, dotsize, foreground, background, 1, grid);
	}
	
	public static PImage apply(PImage image, int dotsize, int foreground, int background, int spacing, boolean grid) {
		
		if(image.width <= 0 || image.height <= 0 || dotsize <=0)
			return image;
		
		PGraphics canvas = image.parent.createGraphics(image.width, image.width);
		canvas.ellipseMode(PConstants.CENTER); 				
		canvas.beginDraw();
		
		canvas.noStroke();
		canvas.background(background);
		canvas.fill(foreground);
		
		int xOffset = 0;
		
		for (int y = 0; y < canvas.height; y += dotsize + spacing) {
			for (int x = xOffset; x < canvas.width; x += dotsize + spacing) {

				int color = image.get(x+dotsize/2, y+dotsize/2);

				double sum = ( 
							+ 0.2125 * (color >> 16 & 0xff) 
							+ 0.7154 * (color >>  8 & 0xff) 
							+ 0.0721 * (color >>  0 & 0xff)
				) / 255.0;

				sum = 1.0 - sum;
				double size = sum * SQUARE_ROOT_2;

				canvas.ellipse(
						(float) x, 
						(float) y, 
						(float) (size * dotsize), 
						(float) (size * dotsize));
			}
			
			if(!grid)
				xOffset = xOffset == 0 ? dotsize/2 : 0;
		}
		
		canvas.endDraw();
		return canvas.get();
	}
	
}
