/* Example for turning an image into a Gameboy Classic look.
 * Use the mouse in X-axis (left and right) to change the size of the pixels.
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

  if (mousePressed == true) {
    image(image, 0, 0);

  } else {
    int pixelSize = (int) map(mouseX, 0, width, 1, 50);
    PImage processedImage = RetroConsole.applyGameboy(image, pixelSize);
    image(processedImage, 0, 0);
  }
}