package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

/**
 * 
 * @author milchreis
 */
public class Dithering {
	
	public enum Algorithm {
		BAYER_2x2, BAYER_4x4, BAYER_8x8
	}
	
	public static final float[] BAYER_2x2 = new float[]{
			2 * 64f - 1,
			3 * 64f - 1,
			4 * 64f - 1,
			1 * 64f - 1
	};
	
	public static final float[] BAYER_4x4 = new float[]{
			0.1250f * 255.0f, 1.0000f * 255.0f, 0.1875f * 255.0f, 
			0.8125f * 255.0f, 0.6250f * 255.0f, 0.3750f * 255.0f,
			0.6875f * 255.0f, 0.4375f * 255.0f, 0.2500f * 255.0f,
			0.8750f * 255.0f, 0.0625f * 255.0f, 0.9375f * 255.0f, 
			0.7500f * 255.0f, 0.5000f * 255.0f, 0.5625f * 255.0f, 
			0.3125f * 255.0f
	};
	
	public static final float[] BAYER_8x8 = new float[]{
			1 * 4f - 1, 	33 * 4f - 1, 	9 * 4f - 1, 	41 * 4f - 1, 	3 * 4f - 1,
			35 * 4f - 1,	11 * 4f - 1,	43 * 4f - 1,	49 * 4f - 1,	17 * 4f - 1,
			57 * 4f - 1,	25 * 4f - 1,	51 * 4f - 1,	19 * 4f - 1,	59 * 4f - 1,
			27 * 4f - 1,	13 * 4f - 1,	45 * 4f - 1,	5 * 4f - 1,		37 * 4f - 1,
			15 * 4f - 1,	47 * 4f - 1, 	7 * 4f - 1,		39 * 4f - 1,	61 * 4f - 1,
			29 * 4f - 1,	53 * 4f - 1,	21 * 4f - 1,	63 * 4f - 1,	31 * 4f - 1,
			55 * 4f - 1,	23 * 4f - 1,	4 * 4f - 1,		36 * 4f - 1,	12 * 4f - 1,
			44 * 4f - 1,	2 * 4f - 1,		34 * 4f - 1,	10 * 4f - 1,	42 * 4f - 1,
			52 * 4f - 1,	20 * 4f - 1,	60 * 4f - 1,	28 * 4f - 1,	50 * 4f - 1,
			18 * 4f - 1,	58 * 4f - 1,	26 * 4f - 1,	16 * 4f - 1,	48 * 4f - 1,
			8 * 4f - 1,		40 * 4f - 1,	14 * 4f - 1,	46 * 4f - 1,	6 * 4f - 1,
			38 * 4f - 1,	64 * 4f - 1,	32 * 4f - 1,	56 * 4f - 1,	24 * 4f - 1,
			62 * 4f - 1,	30 * 4f - 1,	54 * 4f - 1,	22 * 4f - 1
	};
	
	
	public static PImage apply(PImage image) {
		return apply(image, BAYER_4x4);
	}
	
	public static PImage apply(PImage image, Algorithm algorithm) {
		
		if(algorithm == Algorithm.BAYER_2x2) {
			return apply(image, BAYER_2x2);
		}
		
		if(algorithm == Algorithm.BAYER_4x4) {
			return apply(image, BAYER_4x4);
		}
		
		if(algorithm == Algorithm.BAYER_8x8) {
			return apply(image, BAYER_8x8);
		}
		
		return apply(image, BAYER_4x4);
	}
	
    public static PImage apply(PImage image, float[] kernel) {
        
        PImage output = Tools.createBlankImageLike(image);
        
        int r, g, b;
        int kernelSize = (int) Math.sqrt(kernel.length);
        
        for (int x = 0; x < image.width; x++) {
            for (int y = 0; y < image.height; y++) {
            	
            	int pixel = image.get(x, y);
            	
            	r = (pixel >> 16) & 0xff;
            	g = (pixel >> 8) & 0xff;
            	b = (pixel & 0xff);

            	int outR = 0;
            	int outG = 0;
            	int outB = 0;
            	
            	if(r >= kernel[(y % kernelSize * kernelSize + x % kernelSize)]) 		
            		outR = 255;
            	
            	if(g >= kernel[(y % kernelSize * kernelSize + x % kernelSize)]) 
            		outG = 255;
            	
            	if(b >= kernel[(y % kernelSize * kernelSize + x % kernelSize)]) 
            		outB = 255;

                output.set(x, y, (outR << 16) | (outG << 8) | (outB));
            }
        }

        return output;
    }
    
}
