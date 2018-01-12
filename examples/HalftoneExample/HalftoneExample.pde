/* Example for halftone an image to get a old school print look.
 * 
 * The dot size can be changed by moving the mouse left and right.
 * The spacing between the dots can be changed by moving the mouse up and down.
 * If you press the mouse button the dots will be shown in a grid. 
 *  
 * Author: Nick 'Milchreis' Müller
 */

import milchreis.imageprocessing.*;

PImage image;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  // Simple usage:
  // image = Halftone.apply(image, dotsize);
  
  // dotsize by mouseX
  int dotsize = (int)map(mouseX, 0, width, 3, 10);
  
  // dots in grid or honeycomb style by mousePressed
  boolean inGrid = mousePressed;
  
  // Foreground color
  int foreground = #335764;
  
  // Space between dots by mouseY
  int space = (int) map(mouseY, 0, height, 1, 3);
  
  // Draw image
  image(Halftone.apply(image, dotsize, foreground, 255, space, inGrid), 0, 0);
}