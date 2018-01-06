package milchreis.imageprocessing;

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

    public static PImage apply(PImage _image, int size, double sigma) {
    	return Convolution.apply(_image, kernel(size, sigma));
    }
}
