/* Example for toning an image.
 * Use the mouse in X-axis (left and right) to change the toning intensity.
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

  color tone = color(255, 11, 120);

  if (mousePressed == true) {
    // Show original image on mouse pressed ...
    image(image, 0, 0);
    // ... and the tone color
    noStroke();
    fill(tone);
    rect(0, 0, width, 10);

  } else {
    // Show the toned image by intensity by mouse x position
    float intensity = map(mouseX, 0, width, 0.0f, 1.0f);
    image(Toning.apply(image, tone, intensity), 0, 0);
  }
}