package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PApplet;
import processing.core.PImage;

/** 
 * @author milchreis
 */
public class Brightness {

	public static PImage apply(PImage image, int intensity) {

		PImage output = image.copy();
		
		for(int x=0; x < image.width; x++) {
			for(int y=0; y < image.height; y++) {
				
				int[] rgb = Tools.getColors(image, x, y);
				
				rgb[0] += intensity;
				rgb[1] += intensity;
				rgb[2] += intensity;
				
				rgb[0] = PApplet.constrain(rgb[0], 0, 255);
				rgb[1] = PApplet.constrain(rgb[1], 0, 255);
				rgb[2] = PApplet.constrain(rgb[2], 0, 255);
				
				Tools.set(output, x, y, rgb[0], rgb[1], rgb[2]);
			}
		}
		
		return output;
	}
	
}
