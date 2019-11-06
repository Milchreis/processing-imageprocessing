/* Example for toning an image in the shadows and highlights differently.
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

  color highlightTone = color(211, 180, 21);
  color shadowTone = color(124, 32, 201);

  if (mousePressed == true) {
    // Show original image on mouse pressed ...
    image(image, 0, 0);
    // ... and the tone colors
    noStroke();
    fill(highlightTone);
    rect(0, 0, width, 10);

    fill(shadowTone);
    rect(width/2, 0, width/2, 10);

  } else {
    // Show the toned image
    float intensityHighlights = map(mouseX, 0, width, 0.0f, 0.5f);
    float intensityShadows = map(mouseY, 0, width, 0.0f, 0.5f);

    PImage processedImage = SplitToning.apply(
      image,
      highlightTone,
      intensityHighlights,
      shadowTone,
      intensityShadows);

    image(processedImage, 0, 0);
  }
}
