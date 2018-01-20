package milchreis.imageprocessing;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author nick
 */
public class Tools {

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
	
	public static int[] getRGB(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb & 0xff);
		return new int[]{r, g ,b};
	}

	public static float[] rgbToHsb(int rgb) {
		int[] arrRGB = getRGB(rgb);
		return rgbToHsb(arrRGB[0], arrRGB[1], arrRGB[2]);
	}
	
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
