/* Example for knitting effect.
 * Move the mouse from left to right to change the size
 * and from top to bottom to change the threshold.
 * or press the left mouse button to see the original image.
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

  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
    int size = (int) map(mouseX, 0, width, 5, 50);
    float threshold = map(mouseY, 0, height, 0.0, 1.0);
    image(Knitting.apply(image, size, threshold, 240, #EE0000), 0, 0);
  }
}