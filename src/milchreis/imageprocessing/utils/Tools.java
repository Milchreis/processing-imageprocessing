package milchreis.imageprocessing.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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

	public static void set(PImage image, int x, int y, int[] rgb) {
		set(image, x, y, rgb[0], rgb[1], rgb[2]);
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

	public static List<Integer> getNeighbors(PImage image, int x, int y) {
		
		List<Integer> neighbors = new ArrayList<Integer>();
		
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				neighbors.add(image.get(x+i, y+j));
			}
		}
		return neighbors;
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

	public static int getRGB(int red, int green, int blue) {
		 return (red << 16) | (green << 8) | (blue);
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
	
	public static int hsbToRgb(float[] hsb) {
		return hsbToRgb(hsb[0], hsb[1], hsb[2]);
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
	
	public static void copyChannel(PImage source, PImage target, int sourceX, int sourceY, int w, int h, int targetX, int targetY, int sourceChannel, int destChannel) {
		
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
		
				int[] rgb = Tools.getColors(source, x, y);
				int channel = 0;
				
				switch(sourceChannel) {
					case 1: channel = rgb[0]; break;
					case 2: channel = rgb[1]; break;
					case 3: channel = rgb[2]; break;
				}

				int[] rgbT = Tools.getColors(target, x, y);
				
				switch(destChannel) {
					case 1: rgbT[0] = channel; break;
					case 2: rgbT[1] = channel; break;
					case 3: rgbT[2] = channel; break;
				}
				
				target.set(x, y, getRGB(rgbT[0], rgbT[1], rgbT[2]));
			}
		}
	}

}
