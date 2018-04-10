/* Example for flipping images in vertical an horizontal.
 * Use the mouse in X-axis (left and right) to flip the image horizontal. 
 * Move the mouse Y-axis (up and down) to flip the image in vertical.
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
    
    boolean flipHorizonal = mouseX >= width/2;
    boolean flipVertical = mouseY >= height/2;
    
    image(Flip.apply(image, flipHorizonal, flipVertical), 0, 0);
  }
}