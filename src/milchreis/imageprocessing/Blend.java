package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

public class Blend {
	
	public static PImage apply(PImage image1, PImage image2, float intensity) {

		intensity = intensity > 1.0f ? 1.0f : intensity;
		intensity = intensity < -1.0f ? -1.0f : intensity;
		
		PImage out = Tools.createBlankImageLike(image1);
		
		for(int x=0; x < image1.width; x++) {
			for(int y=0; y < image1.height; y++) {
				
				int c = image1.parent.lerpColor(image1.get(x, y), image2.get(x, y), intensity);
				out.set(x, y, c);
			}
		}
		
		return out;
	}

}
