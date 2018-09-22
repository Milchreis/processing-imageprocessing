package milchreis.imageprocessing;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

public class ASCII {

	public static final String LONG_SET  = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";
	public static final String SHORT_SET = "@%#*+=:. ";
	private static PFont font;

	public static PImage apply(PImage input) {
		return apply(input, LONG_SET, 10, 0, 255, true);
	}
	
	public static PImage apply(PImage input, String characterset) {
		return apply(input, characterset, 10, 0, 255, true);
	}
	
	public static PImage apply(PImage input, String characterset, int fontSize) {
		return apply(input, characterset, fontSize, 0, 255, true);
	}
	
	public static PImage apply(PImage input, String characterset, int fontsize, int foreground, int background, boolean toneInColor) {
	  if(input == null || input.width <= 0 || input.height <= 0) 
	    return input;
	  
	  if(font == null || fontsize != font.getSize()) {
		  font = input.parent.createFont("Monospaced", fontsize);
	  }
	  
	  int widthPadding = (int)(fontsize/3);
	  int heightPadding = (int)(fontsize/12);
	  
	  PGraphics canvas = input.parent.createGraphics(input.width, input.height);
	  
	  canvas.beginDraw();
	  canvas.fill(background);
	  canvas.rect(0, 0, canvas.width, canvas.height);
	   
	  canvas.fill(foreground);
	  canvas.textFont(font, fontsize);
	  
	  for (int y = 0; y < input.height; y += fontsize-heightPadding) {
	      for (int x = 0; x < input.width; x += fontsize-widthPadding) {
	        
	        int pixel = input.get(x, y);
	        int r = (pixel >> 16) & 0xff;
	        int g = (pixel >> 8) & 0xff;
	        int b = (pixel & 0xff);
	        int tone = (r + g + b)/3; 

	        if(toneInColor) {
	        	canvas.fill(pixel);
	        }
	       
	        int characterIndex = (int) PApplet.map(tone, 0, 255, 0, characterset.length()-1);
	        canvas.text(characterset.charAt(characterIndex), x, y);
	      }
	  }
	  
	  canvas.endDraw();
	  return canvas.get();
	}
}
