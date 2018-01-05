package milchreis.imageprocessing;

import processing.core.PConstants;
import processing.core.PImage;

/**
 * Source by https://github.com/avatarr/java-image-processing-algorithm
 * Conversion to working with processing by https://github.com/milchreis/
 * 
 * @author pratchaya
 * @author milchreis
 */
public class Gaussian {

    public static double[][] kernel(int _size, double sigma) {

        int size = _size;
        double gaussian[][] = new double[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                int xValue = i - (size / 2);
                int yValue = j - (size / 2);
                gaussian[i][j] = (1 / (2 * Math.PI * Math.pow(sigma, 2))) * (Math.pow(Math.E, -((Math.pow(xValue, 2) + Math.pow(yValue, 2)) / (2 * Math.pow(sigma, 2)))));
            }
        }

        return gaussian;
    }

    public static double kernel_sum(double[][] kernel) {
        double kernel_sum = 0.0;
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel.length; j++) {
                kernel_sum += kernel[i][j];
            }
        }
        return kernel_sum;
    }

    public static PImage apply(PImage _image, int size, double sigma) {

    	PImage imageOutput = Tools.createImage(_image.width, _image.height, PConstants.RGB, _image);

        double gaussian[][] = Gaussian.kernel(size, sigma);
        double agv = Gaussian.kernel_sum(gaussian);
        int wight = _image.width; // image wight
        int heigth = _image.height; // image hight 
        int kernelSize = gaussian.length; // size kernel
        int kernelXY = kernelSize / 2; // find a center of kernel
        // make a result with convolution method
        // return image result

        for (int i = 0; i < wight; i++) {
            for (int j = 0; j < heigth; j++) {
                int r = 0, g = 0, b = 0; // store RGB

                for (int k = -kernelXY; k <= kernelXY; k++) {
                    for (int l = -kernelXY; l <= kernelXY; l++) {
                        if (k * k + l * l < gaussian.length * gaussian.length) {
                            int p = RGB.getRGBW(_image, i + k, j + l);
                            // calculate a RGB by chip bit
                            r += ((p >> 16) & 0xFF) * gaussian[k + kernelXY][l + kernelXY];
                            g += ((p >> 8) & 0xFF) * gaussian[k + kernelXY][l + kernelXY];
                            b += (p & 0xFF) * gaussian[k + kernelXY][l + kernelXY];
                        }
                    } //end l
                }//end k
                int rgb = ((int) (r / agv) << 16) | ((int) (g / agv) << 8) | ((int) (b / agv));
                //set RGB revert to image
                imageOutput.set(i, j, rgb);
            }// end i
        } //end j;
        return imageOutput;
    }
}
