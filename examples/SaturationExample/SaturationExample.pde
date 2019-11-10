/* Example for changing the saturation of an image.
 * Use the mouse in X-axis (left and right) to change the intensity.
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
    float intensity = map(mouseX, 0, width, 0.0f, 2.0f);
    image(Saturation.apply(image, intensity), 0, 0);
  }
}