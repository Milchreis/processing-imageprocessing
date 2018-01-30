package milchreis.imageprocessing;

import processing.core.PGraphics;
import processing.core.PImage;


public class Scanlines {

  public static PImage apply(PImage image) {
    return apply(image, 2, 200);
  }
  
   public static PImage apply(PImage image, int scanlineHeight) {
    return apply(image, scanlineHeight, 200);
  }
  
  public static PImage apply(PImage image, int scanlineHeight, int alpha) {
    
    if(scanlineHeight <= 0)
      return image;
    
    PGraphics canvas = image.parent.createGraphics(image.width, image.width);        
    canvas.beginDraw();
    
    canvas.image(image, 0, 0);
    
    canvas.noStroke();
    canvas.fill(0, 0, 0, alpha);
    
    for(int h = scanlineHeight; h < image.height; h += 2*scanlineHeight) {
      canvas.rect(0, h, image.width, scanlineHeight);
    }
    
    canvas.endDraw();
    return canvas.get();
  }
  
}
