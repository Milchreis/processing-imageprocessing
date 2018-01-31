/* Example for shifting colors.
 * Click on a color in the picture to select a color.
 * Move the mouse from left to right to see the color shifting.
 *  
 * Author: Nick 'Milchreis' Müller
 */

import milchreis.imageprocessing.*;

PImage image;
int hue = 0;
int x = 0, y = 0;
int shiftedColor = 0;
int pixel = 0;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  if(mousePressed == true) {
    // Select color from clicked position
    pixel = image.get(mouseX, mouseY);
    
    // Save mouse position to compare the shifted value
    x = mouseX;
    y = mouseY;
 
    // Get hue value
    hue = (int)Tools.rgbToHsb(pixel)[0];

  } else {
    // Get shift by mouse x-axis    
    int shift = (int)map(mouseX, 0, width, -30, 30);
    
    // Shift the selected color by hue value
    PImage p = ColorShift.applyHue(image, hue, 10, shift);
    
    // Get new shifted color
    shiftedColor = p.get(x, y);
    
  	image(p, 0, 0);
  }
 
  noStroke();
  
  // Draw selected color
  fill(pixel);
  rect(0, height - 10, width/2, 10);
  
  // Draw shifted color
  fill(shiftedColor);
  rect(width/2, height - 10, width/2, 10);
}