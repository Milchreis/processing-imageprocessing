package milchreis.imageprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import processing.core.PImage;

/**
 * Lookup table implementation and load and save utilities.
 * 
 * @author milchreis
 */
public class LUT {

	/**
	 * Available styles
	 */
	public enum STYLE {
		RETRO, CONTRAST, CONTRAST_STRONG, ANALOG1, WINTER, SPRING, SUMMER, AUTUMN;
		
		public String getFileName() {
			return this.name().toLowerCase() + ".lut";
		}
	}
	
	int[] lutR = new int[256];
	int[] lutG = new int[256];
	int[] lutB = new int[256];
	
	public LUT() {
		for(int i=0; i<256; i++) {
			lutR[i] = i;
			lutG[i] = i;
			lutB[i] = i;
		}
	}

	/**
	 * Loads a lookup table text file.
	 * 
	 * @param lutfile	expects a lookup table file (f.e. in data directory)
	 * @return			returns a lookup table
	 */
	public static LUT loadLut(String lutfile) {
		return loadLut(new File(lutfile));
	}
	
	/**
	 * Loads a lookup by a predefined style
	 * 
	 * @param lutfile	expects a style (f.e. LUT.STYLE.CONTRAST)
	 * @return			returns a lookup table
	 */
	public static LUT loadLut(STYLE style) {
		return loadLut(
				LUT.class.getResourceAsStream(
						"/data/" + style.getFileName()));
	}
	
	/**
	 * Loads a lookup by a predefined file object
	 * 
	 * @param lutfile	expects a lookup table file (f.e. in data directory)
	 * @return			returns a lookup table
	 */
	public static LUT loadLut(File target) {
		
		try {
			return loadLut(new FileInputStream(target));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new LUT();
	}
	
	/**
	 * Loads a lookup by a predefined file object
	 * 
	 * @param lutfile	expects a lookup table file (f.e. in data directory)
	 * @return			returns a lookup table
	 */
	public static LUT loadLut(InputStream stream) {
		LUT lut = new LUT();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			String line = null;
			
			for(int i=0; i < 256; i++) {

				line = br.readLine();
				String[] elements = line.split(" ");

				lut.lutR[i] = Integer.parseInt(elements[0]);
				lut.lutG[i] = Integer.parseInt(elements[1]);
				lut.lutB[i] = Integer.parseInt(elements[2]);
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lut;
	}

	/**
	 * Saves a lookup
	 * 
	 * @param target	expects the target file
	 * @param lut		expects the lookup table
	 */
	public static void saveLut(String target, LUT lut) {
		saveLut(new File(target), lut);
	}
	
	/**
	 * Saves a lookup
	 * 
	 * @param target	expects the target file
	 * @param lut		expects the lookup table
	 */
	public static void saveLut(File target, LUT lut) {
		try {
			PrintWriter writer = new PrintWriter(target, "UTF-8");

			for(int i=0; i < 256; i++) {
				writer.println(lut.lutR[i] + " " + lut.lutG[i] + " " + lut.lutB[i]);
			}
			
			writer.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Uses the lookup table and returns a copied image
	 * 
	 * @param target	expects the target file
	 */
	public static PImage apply(PImage original, LUT lut) {

		PImage preview = original.copy();
	    preview.loadPixels();
	    for (int i = 0; i < preview.pixels.length-1; i++) {

	      int pixel = preview.pixels[i];

	      // right shift to separate channels
	      int a = (pixel >> 24) & 0xFF;
	      int r = (pixel >> 16) & 0xFF;
	      int g = (pixel >> 8) & 0xFF;
	      int b = (pixel) & 0xFF;

	      // start color look up and reassemble color object
	      // red(), ... and color() function are slower and not thread-safe
	      int argb = a << 24 | lut.lutR[r] << 16 | lut.lutG[g] << 8 | lut.lutB[b];

	      preview.pixels[i] = argb;
	    }
	    preview.updatePixels();
	    return preview;
	}

}
