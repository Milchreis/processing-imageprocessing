package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Sobel detects edges with convolution.
 * 
 * @author milchreis
 */
public class SobelEdgeDetector {

	private static final double[][] SOBEL_X = {
			{+1, 0, -1},
			{+2, 0, -2},
			{+1, 0, -1},
	};
	
	private static final double[][] SOBEL_Y = {
			{+1, +2, +1},
			{ 0,  0,  0},
			{-1, -2, -1},
	};

	public static PImage apply(PImage image) {
		return apply(image, true);
	}
		
	/**
	 * 
	 * @param image		expects an image
	 * @param gray		if true the sobel result is in gray-scale, 
	 * 					otherwise the operation is for each color channel
	 * @return
	 */
	public static PImage apply(PImage image, boolean gray) {
		
		PImage output = Tools.createBlankImageLike(image);
		
        int kernelSize = SOBEL_X.length;
        int kernelXY = kernelSize / 2; 

        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {            	
                int rX = 0, gX = 0, bX = 0;
                int rY = 0, gY = 0, bY = 0;

                for (int k = -kernelXY; k <= kernelXY; k++) {
                    for (int l = -kernelXY; l <= kernelXY; l++) {
                        if (k * k + l * l < SOBEL_X.length * SOBEL_X.length) {
                            int p = image.get(x+k, y+l); 
                            // calculate a RGB by chip bit
                            rX += ((p >> 16) & 0xFF) * SOBEL_X[k + kernelXY][l + kernelXY];
                            gX += ((p >> 8) & 0xFF) * SOBEL_X[k + kernelXY][l + kernelXY];
                            bX += (p & 0xFF) * SOBEL_X[k + kernelXY][l + kernelXY];
                            // calculate a RGB by chip bit
                            rY += ((p >> 16) & 0xFF) * SOBEL_Y[k + kernelXY][l + kernelXY];
                            gY += ((p >> 8) & 0xFF) * SOBEL_Y[k + kernelXY][l + kernelXY];
                            bY += (p & 0xFF) * SOBEL_Y[k + kernelXY][l + kernelXY];
                        }
                    }
                }
                
                int r = (int) Math.sqrt(rX * rX + rY * rY);
                int g = (int) Math.sqrt(gX * gX + gY * gY);
                int b = (int) Math.sqrt(bX * bX + bY * bY);
                
                r = PApplet.constrain(r, 0, 255);
                g = PApplet.constrain(g, 0, 255);
                b = PApplet.constrain(b, 0, 255);

                if(gray) {
                	int grayTone = (int)((r* 0.2125) + (g * 0.7154) + (b * 0.0721));
                	r = grayTone;
                	g = grayTone;
                	b = grayTone;
                }
                	
                int rgb = ((int) (r) << 16) | ((int) (g) << 8) | ((int) (b));
                output.set(x, y, rgb);
            }
        }
		
		return output;
	}
}
