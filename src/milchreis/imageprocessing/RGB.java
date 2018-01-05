package milchreis.imageprocessing;

import processing.core.PImage;

/**
 * Source by https://github.com/avatarr/java-image-processing-algorithm
 * 
 * @author pratchaya
 */
public class RGB {

	public static int getRGBW(PImage image, int i, int j) {
		int width = image.width;
		int height = image.height;
		i = Math.max(0, Math.min(width - 1, i));
		j = Math.max(0, Math.min(height - 1, j));
		return image.get(i, j);
	}

	public static int getRGBH(PImage image, int i, int j) {
		int width = image.width;
		int height = image.height;
		j = Math.max(0, Math.min(width - 1, j));
		i = Math.max(0, Math.min(height - 1, i));
		return image.get(i, j);
	}
}
