package milchreis.imageprocessing;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Vintage {

	public static PImage createGradient(PImage image, int topColor, int bottomColor, int alpha) {
		
		PGraphics canvas = image.parent.createGraphics(image.width, image.height);        
	    canvas.beginDraw();
	    canvas.noStroke();
	    
	    for(int y=0; y<image.height; y++) {
	    	
	    	int color = image.parent.lerpColor(topColor, bottomColor, (float)y / (float)image.height);
	    	
	    	canvas.fill(color);
	    	canvas.rect(0, y, image.width, 1);
	    }
	    
	    canvas.endDraw();
		
	    return canvas;
	}
	
	public static void blendSoftEdges(PImage mask, PImage image) {
	
		for(int x = 0; x < mask.width; x++) {
			for(int y = 0; y < mask.height; y++) {
				
				int[] mpixel = Tools.getColors(mask, x, y); 
				int[] ipixel = Tools.getColors(image, x, y);
				
				float mAlpha = mask.parent.alpha(mask.get(x, y));
				float iAlpha = image.parent.alpha(image.get(x, y));

				mAlpha = PApplet.map(mAlpha, 0, 255, 0f, 1f);
				iAlpha = PApplet.map(iAlpha, 0, 255, 0f, 1f);
				
				int[] targetPixel = new int[3];
				
				for(int i=0; i<3; i++) {
					
					float mp = (float)mpixel[i] * mAlpha;
					float ip = (float)ipixel[i] * iAlpha;
					
					int overlay = (int) ((ip / 255.0f) * (ip + ((2*mp)/225.0f) * (255 - ip)));
					overlay = overlay > 255 ? 255 : overlay;
					overlay = overlay < 0 ? 0 : overlay;
					
					float screenBlend = (255 - (((255 - mp) * (255 - ip)) / (255.0f)));
					screenBlend = screenBlend > 255 ? 255 : screenBlend;
					screenBlend = screenBlend < 0 ? 0 : screenBlend;
					
					int softEdge = (int) (((((255 - ip) * mp) + screenBlend) / 255.0f));
					softEdge = softEdge > 255 ? 255 : softEdge;
					softEdge = softEdge < 0 ? 0 : softEdge;
					
					targetPixel[i] = overlay; //softEdge;
				}
				
				image.set(x, y, Tools.getRGB(targetPixel[0], targetPixel[1], targetPixel[2]));
			}
		}
	}
	
	public static PImage apply(PImage image, float intensity) {
		return apply(image, Tools.getRGB(227,  213,  183), Tools.getRGB(4, 135, 153), intensity);
	}
	
	public static PImage apply(PImage image, int topColor, int bottomColor, float intensity) {
		
		PImage out = image.copy();
		PImage gradient = createGradient(image, topColor, bottomColor, (int)PApplet.map(intensity, 0, 1, 0, 255));
		
		blendSoftEdges(gradient, out);
		
		// TODO: intensity
		
		return out;
	}
}
