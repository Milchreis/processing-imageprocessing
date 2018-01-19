/* Example for dithering an image to get a retro look.
 * 
 * Use the mouse wheel to change the dithering algorithm
 * and press spacebar to show it in gray scale for the perfect 
 * retro look.
 *
 * Author: Nick 'Milchreis' Müller
 */

import milchreis.imageprocessing.*;

PImage image;
int index = 0;

void setup() {
  size(550, 550);
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  PImage processed = image;
  String label = "";
  
  if (keyPressed && key == ' ') {
    processed = Grayscale.apply(processed);
  }
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
    
    if(index == -1) {
      processed = Dithering.apply(processed);
      label = "BAYER_4x4 on default";
    }
      
    if(index == 0) {
      processed = Dithering.apply(processed, Dithering.Algorithm.BAYER_2x2);  
      label = "BAYER_2x2";
    }
    if(index == 1) {
      processed = Dithering.apply(processed, Dithering.Algorithm.BAYER_4x4); 
      label = "BAYER_4x4";
    }
    
    if(index == 2) {
      processed = Dithering.apply(processed, Dithering.Algorithm.BAYER_8x8);
      label = "BAYER_8x8";
    }
  }
  
  image(processed, 0, 0);
  fill(0);
  text(label, width/2 - textWidth(label)/2, 30);
}

void mouseWheel(MouseEvent event) {
  index += event.getCount();
  
  if(index >= Dithering.Algorithm.values().length) {
    index = -1;
  }
}
