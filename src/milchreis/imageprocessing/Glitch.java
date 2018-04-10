package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

public class Glitch {

	public static PImage apply(PImage image) {
		return apply(image, 3, 1);
	}
	
	public static PImage apply(PImage image, int glitchAmount) {
		return apply(image, glitchAmount, 1);
	}
	
	public static PImage apply(PImage image, int glitchAmount, int scanlines) {
		
		if(image.width <= 0 || image.height <= 0)
			return image;
		
		PImage output = image.copy();

		int maxOffset = (int)(glitchAmount * glitchAmount / 100.0 * image.width);
		
		for(int i=0; i < glitchAmount * 2; i++) {
			
			int startY = (int) image.parent.random(0, image.height);
			int chunkHeight = (int) image.parent.random(1, image.height / 4);
			chunkHeight = Math.min(chunkHeight, image.height - startY);
			int offset = (int) image.parent.random(-maxOffset, maxOffset);
			
			if(offset == 0)
				continue;
			
			if(offset < 0) {
				
				output.copy(image, -offset, startY, image.width + offset, chunkHeight, 0, startY, image.width + offset, chunkHeight);
				output.copy(image, 0, startY, -offset, chunkHeight, image.width + offset, startY, -offset, chunkHeight);
				
			} else {

				output.copy(image, 0, startY, image.width, chunkHeight, offset, startY, image.width, chunkHeight);
				output.copy(image, image.width - offset, startY, offset, chunkHeight, 0, startY, offset, chunkHeight);
			}
		}
		
		int ccX = (int) image.parent.random(-glitchAmount * 2, glitchAmount*2);
		int ccY = (int) image.parent.random(-glitchAmount * 2, glitchAmount*2);
		
		int channel = getRandChannel();
		Tools.copyChannel(image, output, 0, 0, image.width, image.height, ccX, ccY, channel, channel);
		
		output = Brightness.apply(output, 30);
		
		if(scanlines == 0)
			return output;
		
		return Scanlines.apply(output, scanlines, 130);
	}
	
	private static int getRandChannel() {
		double r = Math.random();
		if (r < .33) {
			return 2;
		} else if (r < .66) {
			return 1;
		} else {
			return 3;
		}
	}
}
