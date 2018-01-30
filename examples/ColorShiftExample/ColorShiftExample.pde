/* Example for shifting colors.
 *  
 * Author: Nick 'Milchreis' Müller
 */

import milchreis.imageprocessing.*;

PImage image;
color blue = #3896F2;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
    int shift = (int)map(mouseX, 0, width, -30, 30);
    int hue = (int)map(mouseY, 0, height, 0, 360);
    //float shift = map(mouseX, 0, width, -0.3, 0.3);
    PImage p = ColorShift.applyHue(image, hue, 10, shift);
    
  	image(p, 0, 0);
  }
}