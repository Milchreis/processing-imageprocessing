package milchreis.imageprocessing;

import java.util.Collections;
import java.util.List;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

/**
 * @author milchreis
 */
public class Denoise {

	public static PImage applyMedian(PImage image, int iterations) {

		PImage output = Tools.createBlankImageLike(image);
		PImage tmpImage = image;

		for (int iteration = 0; iteration < iterations; iteration++) {
			
			for (int x = 0; x < tmpImage.width - 1; x++) {
				for (int y = 0; y < tmpImage.height - 1; y++) {
			
					List<Integer> neighbors = Tools.getNeighbors(tmpImage, x, y);
					Collections.sort(neighbors);

					int median = neighbors.get(neighbors.size() / 2);
					output.set(x, y, median);
				}
			}

			tmpImage = output.copy();
		}

		return output;
	}

}
