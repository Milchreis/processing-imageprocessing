package milchreis.imageprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import processing.core.PImage;

/**
 * Lookup table implementation and load and save utilities.
 * 
 * @author milchreis
 */
public class LUT implements Serializable {
	private static final long serialVersionUID = 1L;

	int[] lutR = new int[256];
	int[] lutG = new int[256];
	int[] lutB = new int[256];

	public static LUT loadLut(String lutfile) {
		return loadLut(new File(lutfile));
	}
	
	public static LUT loadLut(File target) {
		LUT lut = new LUT();
		
		try {
			FileReader fr = new FileReader(target);
			BufferedReader br = new BufferedReader(fr);
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

	public static void saveLut(String target, LUT lut) {
		saveLut(new File(target), lut);
	}
	
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

	public static PImage useLut(PImage original, LUT lut) {

		PImage preview = original.copy();
	    preview.loadPixels();
	    for (int i = 0; i < preview.pixels.length-1; i++) {

	      int pixel = preview.pixels[i];

	      // right shift to separate channels
	      int a = (pixel >> 24) & 0xFF;
	      int r = (pixel >> 16) & 0xFF;
	      int g = (pixel >> 8) & 0xFF;
	      int b = (pixel) & 0xFF;

	      // start color uok up and reassemble color object
	      // red(), ... and color() function are slower and not thread-safe
	      int argb = a << 24 | lut.lutR[r] << 16 | lut.lutG[g] << 8 | lut.lutB[b];

	      preview.pixels[i] = argb;
	    }
	    preview.updatePixels();
	    return preview;
	}

}
