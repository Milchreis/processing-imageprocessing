/* Example for changing the dark parts of an image.
 * Move the mouse from left to right to see different intensity
 * or press the left mouse button to see the original image.
 *
 * In the middle position the image is unchanged. Move the mouse 
 * in the left area of the image the dark parts will be 
 * more dark and the right area you will lift up the shadows.
 *
 * You want more? See the Lights.apply() for doing the same in the highlights.
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
    image(Shadows.apply(image, intensity), 0, 0);
  }
}