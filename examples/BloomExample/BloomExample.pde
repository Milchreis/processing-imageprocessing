/* Example for bloom effect.
 * Move the mouse from left to right to see different intensity
 * or press the left mouse button to see the original image.
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
    int intensity = (int) map(mouseX, 0, width, 0, 255);
  	image(Bloom.apply(image, intensity), 0, 0);
  }
}