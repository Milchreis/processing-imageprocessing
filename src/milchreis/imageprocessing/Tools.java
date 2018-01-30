package milchreis.imageprocessing;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author nick
 */
public class Tools {

	public static void set(PImage img, int x, int y, int color) {
		img.pixels[x + img.width * y] = color;
	}
	
	public static void set(PImage img, int x, int y, int r, int g, int b) {
		img.pixels[x + img.width * y] = (r << 16) | (g << 8) | (b);
	}
	
	public static int get(PImage img, int x, int y) {
		if ((x < 0) || (y < 0) || (x >= img.width) || (y >= img.height)) return 0;
		return img.pixels[x + img.width * y];
	}
	
	public static int[] getColors(PImage img, int x, int y) {
		return getRGB(get(img, x, y));
	}
	
	public static PImage createImage(int w, int h, int format) {
		PImage image = new PImage(w, h, format);
		return image;
	}

	public static PImage createImage(int w, int h, int format, PImage original) {
		PImage image = createImage(w, h, format);
		image.parent = original.parent; // make save() work
		return image;
	}

	public int[] getArray(int arr[], int val) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = val;
		}
		return arr;
	}

	public static PImage createBlankImageLike(PImage image) {
		return createImage(image.width, image.height, image.format, image);
	}
	
	/**
	 * Returns an array for red, green, blue in
	 * range from 0 to 255.
	 * 
	 * @param rgb
	 * @return
	 */
	public static int[] getRGB(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb & 0xff);
		return new int[]{r, g ,b};
	}

	/**
	 * Returns an array for hue, saturation and brightness
	 * in range:
	 * <ul>
	 * 	<li>hue: 0 - 360
	 * 	<li>saturation: 0.0 - 1.0
	 * 	<li>brightness: 0.0 - 1.0
	 * </ul>
	 * @param rgb
	 * @return
	 */
	public static float[] rgbToHsb(int rgb) {
		int[] arrRGB = getRGB(rgb);
		return rgbToHsb(arrRGB[0], arrRGB[1], arrRGB[2]);
	}
	
	/**
	 * Returns an array for hue, saturation and brightness
	 * in range:
	 * <ul>
	 * 	<li>hue: 0 - 360
	 * 	<li>saturation: 0.0 - 1.0
	 * 	<li>brightness: 0.0 - 1.0
	 * </ul>
	 * @param rgb
	 * @return
	 */
	public static float[] rgbToHsb(int r, int g, int b) {

		float[] hsb = Color.RGBtoHSB(r, g, b, new float[3]);
		hsb[0] = PApplet.map(hsb[0], 0f, 1f, 0, 360);
		
		return hsb;
	}

	public static int hsbToRgb(float h, float s, float b) {
		h = PApplet.map(h, 0f, 360f, 0, 1f);
		return Color.HSBtoRGB(h, s, b);
	}
	
	public static boolean in(int x, int start, int end) {
		return in((float)x, start, end);
	}

	public static boolean in(float x, float start, float end) {
		return x >= start && x <= end;
	}
}
