/* Example for mapping an image in to a given palette of colors.
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
    PImage processedImage = PaletteMapping.apply(image, #381460, #b21f66, #fe346e, #ffbd69);
    image(processedImage, 0, 0);
  }
}