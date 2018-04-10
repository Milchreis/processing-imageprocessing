/* Example for inverting colors in images for each color channel.
 * Use the 'r' key to switch the inverting for the red color channel. 
 * Use the 'g' key to switch the inverting for the green color channel.
 * Use the 'b' key to switch the inverting for the blue color channel.
 *
 * Author: Nick 'Milchreis' Müller
 */
 
import milchreis.imageprocessing.*;

PImage image;

boolean invertRedChannel = true;
boolean invertGreenChannel = true;
boolean invertBlueChannel = true;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
    
    PImage pimage = InvertColors.apply(
        image, 
        invertRedChannel, 
        invertGreenChannel, 
        invertBlueChannel);
    
    image(pimage, 0, 0);
  }
}

void keyPressed() {
  if (key == 'r') {
    invertRedChannel = !invertRedChannel;
  } 
  
  if (key == 'g') {
    invertGreenChannel = !invertGreenChannel;
  }
  
  if (key == 'b') {
    invertBlueChannel = !invertBlueChannel;
  }
}