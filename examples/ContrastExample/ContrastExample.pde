/* Example for changing contrast in an image.
 * Use the mouse in X-axis (left and right) to change the contrast intensity.
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
    // Show the toned image by intensity by mouse x position
    float intensity = map(mouseX, 0, width, -1.0f, 1.0f);
    image(Contrast.apply(image, intensity), 0, 0);
  }
}