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
    int lineWeight = 2;
    int linesPerGrid = 4;
    int lineIntensity = 220;
    boolean inColor = true;
    color backgroundColor = 255;

    PImage processedImage = Strokes.apply(image,
        gridSize, lineLength, lineWeight,
        linesPerGrid, lineIntensity, inColor, backgroundColor);

    image(processedImage, 0, 0);
  }
}
