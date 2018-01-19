package milchreis.imageprocessing;

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
    	image.parent = original.parent;  // make save() work
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
}
