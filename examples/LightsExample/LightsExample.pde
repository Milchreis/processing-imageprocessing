/* Example for changing the light parts of an image.
 * Move the mouse from left to right to see different intensity
 * or press the left mouse button to see the original image.
 *
 * In the middle position the image is unchanged. Move the mouse 
 * in the left area of the image the light parts will be 
 * more dark and the right area you will lift up the lights.
 *
 * You want more? See the Shadows.apply() for doing the same in the shadows.
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
    float intensity = map(mouseX, 0, width, -0.3f, 0.3f);
    image(Lights.apply(image, intensity), 0, 0);
  }
}