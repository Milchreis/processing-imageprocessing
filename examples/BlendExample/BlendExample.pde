/* Example for blending 2 images
 * Use the mouse and move it from left to right to change 
 * the blending intensity.
 *  
 * Author: Nick 'Milchreis' Müller
 */
 
import milchreis.imageprocessing.*;

PImage dog1, dog2;

void setup() {
  size(552, 578);
  
  dog1 = loadImage(dataPath("dog1.jpg"));
  dog1.resize(width, height);
  
  dog2 = loadImage(dataPath("dog2.jpg"));
  dog2.resize(width, height);
}

void draw() {

  float intensity = map(mouseX, 0, width, 0.0f, 1.0f);
  
  PImage out = Blend.apply(dog1, dog2, intensity);
  
  image(out, 0, 0);
}