/* Example for creating a matte effect in the dark areas.
 * Use the mouse in X-axis (left and right) to change the matte intensity.
 * Use the mouse in Y-axis (up and down) to change the contrast intensity.
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
    int matteIntensity = (int) map(mouseX, 0, width, 0, 50);
    int contrastIntensity = (int) map(mouseY, 0, height, 0, 50);
    float saturationIntensity = -0.02;

    PImage processedImage = Matte.apply(image, matteIntensity, contrastIntensity, saturationIntensity);
    image(processedImage, 0, 0);
  }
}
