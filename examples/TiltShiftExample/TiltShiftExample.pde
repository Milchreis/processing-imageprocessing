/* Example for tilt-shift effect.
 * Use the mouse in Y axis to the moving sharp area.
 * You can change the wideness of the sharp area with mouse wheel.
 *  
 * Author: Nick 'Milchreis' Müller
 */
 
import milchreis.imageprocessing.*;

PImage image;
int sharpWideness = 100;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {

    int blurIntensity = 3;    
    boolean horizontal = true;
    int position = mouseY;
    
    image(TiltShift.apply(image, blurIntensity, horizontal, position, sharpWideness), 0, 0);
  }
}

void mouseWheel(MouseEvent event) {
  sharpWideness += (event.getCount()*5);
  
  if(sharpWideness <= 0) {
    sharpWideness = 0;
  }
}