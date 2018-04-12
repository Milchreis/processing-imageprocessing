/* Example for adding a vignette to an image.
 * Use the mouse in X-axis (left and right) to change the vignette intensity.
 * Move the mouse Y-axis (up and down) to change the width of the vignettel.
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

    float intensity = map(mouseX, 0, width, 0.0f, 1.0f);
    float vignetteWidth = map(mouseY, 0, height, 0.0f, 1.0f);

    image(Vignette.apply(image, intensity, vignetteWidth), 0, 0);
  }
}