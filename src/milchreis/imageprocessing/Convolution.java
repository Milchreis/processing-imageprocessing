package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Convolution {

    public static double kernelSum(double[][] kernel) {
        double kernel_sum = 0.0;
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel.length; j++) {
                kernel_sum += kernel[i][j];
            }
        }
        return kernel_sum;
    }
	
	public static PImage apply(PImage image, double[][] kernel) {
		
		PImage output = Tools.createImage(image.width, image.height, PConstants.RGB, image);
		
        double agv = kernelSum(kernel);
        int kernelSize = kernel.length;

        // find a center of kernel
        int kernelXY = kernelSize / 2; 

        for (int i = 0; i < image.width; i++) {
            for (int j = 0; j < image.height; j++) {
                int r = 0, g = 0, b = 0; // store RGB

                for (int k = -kernelXY; k <= kernelXY; k++) {
                    for (int l = -kernelXY; l <= kernelXY; l++) {
                        if (k * k + l * l < kernel.length * kernel.length) {
                            int p = image.get(i+k, j+l); 
                            // calculate a RGB by chip bit
                            r += ((p >> 16) & 0xFF) * kernel[k + kernelXY][l + kernelXY];
                            g += ((p >> 8) & 0xFF) * kernel[k + kernelXY][l + kernelXY];
                            b += (p & 0xFF) * kernel[k + kernelXY][l + kernelXY];
                        }
                    } //end l
                }//end k
                
                r = PApplet.constrain(r, 0, 255);
                g = PApplet.constrain(g, 0, 255);
                b = PApplet.constrain(b, 0, 255);
                
                int rgb = ((int) (r / agv) << 16) | ((int) (g / agv) << 8) | ((int) (b / agv));
                //set RGB revert to image
                output.set(i, j, rgb);
            }// end i
        } //end j;
        return output;
	}
	
}
