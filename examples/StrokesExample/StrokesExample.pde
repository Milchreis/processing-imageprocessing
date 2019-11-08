/* Example for changing an image into a line drawing.
 * Use the mouse in X-axis (left and right) to change the toning intensity for the highlights.
 * Use the mouse in Y-axis (up and down) to change the toning intensity for the shadows.
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
    // Show original image on mouse pressed ...
    image(image, 0, 0);

  } else {

    int gridSize = (int) map(mouseX, 0, width, 4, 20);
    int lineLength = (int) map(mouseY, 0, height, 1, 20);

    PImage processedImage = Strokes.apply(image, gridSize, lineLength);
    image(processedImage, 0, 0);
  }
}
