package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PApplet.constrain;
import static processing.core.PApplet.map;

/**
 * Formula form https://www.dfstudios.co.uk/articles/programming/image-programming-algorithms/image-processing-algorithms-part-5-contrast-adjustment/
 *
 * @author milchreis
 */
public class Contrast {

	public static PImage apply(PImage image, float intensity) {

		intensity = constrain(intensity, -1.0f, 1.0f);
		PImage output = image.copy();

		int contrastIntensity = (int) map(intensity, -1.0f, 1.0f, -255, 255);

		float contrastCorrectionFactor =
				(259.f * (contrastIntensity + 255))
				/
				(255f * (259 - contrastIntensity));

		for(int x=0; x < image.width; x++) {
			for(int y=0; y < image.height; y++) {
				
				int[] rgb = Tools.getColors(image, x, y);

				rgb[0] = (int)constrain(contrastCorrectionFactor * (rgb[0] - 128) + 128, 0, 255);
				rgb[1] = (int)constrain(contrastCorrectionFactor * (rgb[1] - 128) + 128, 0, 255);
				rgb[2] = (int)constrain(contrastCorrectionFactor * (rgb[2] - 128) + 128, 0, 255);

				Tools.set(output, x, y, rgb[0], rgb[1], rgb[2]);
			}
		}
		
		return output;
	}


	
}
