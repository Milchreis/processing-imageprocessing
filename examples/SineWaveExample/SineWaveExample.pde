/* Example for sine wave effect.
 * Move the mouse from left to right to change the stroke weight
 * and from top to bottom to change the amplitude.
 * or press the left mouse button to see the original image.
 *  
 * Author: Nick 'Milchreis' Müller
 */
 
import milchreis.imageprocessing.*;

PImage image;

void setup() {
  size(550, 488);
  // Load image
  image = loadImage(dataPath("example2.jpg"));
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
    float weight = map(mouseX, 0, width, 1, 10);
    float rowHeight = map(mouseY, 0, height, 1, 20);
    color background = 255;
    color waves = 0;
  	image(SineWave.apply(image, rowHeight, weight, background, waves), 0, 0);
  }
}