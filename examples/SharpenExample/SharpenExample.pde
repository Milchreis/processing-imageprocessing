/* Example for sharpening an image to get a crunchy look.
 * The intensity of this effect increases slowly.
 * Press the left mouse button to see the original image.
 *  
 * Author: Nick 'Milchreis' Müller
 */

import milchreis.imageprocessing.*;

PImage image;
float sharpIntensity = 0;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
  	image(Sharpen.apply(image, sharpIntensity), 0, 0);
  }
  
  // Reset the intensity or increase it
  if(sharpIntensity > 6)
    sharpIntensity = 0;
  else 
    sharpIntensity += 0.05f;
}