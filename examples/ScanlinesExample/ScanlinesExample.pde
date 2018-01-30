/* This example presents the usage of Scanlines of a retro look.
 * Use the mouse x-axis to change the scanline height.
 * To change the scanline alpha value use the mouse y-axis.
 *  
 * Author: Nick 'Milchreis' Müller
 */

import milchreis.imageprocessing.*;

PImage image;

void setup() {
  size(550, 550);
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
    int scanlinesHeight = (int) map(mouseX, 0, width, 0, 10);
    int alpha = (int) map(mouseY, 0, height, 0, 255);
    image(Scanlines.apply(image, scanlinesHeight, alpha), 0, 0);
  }
}