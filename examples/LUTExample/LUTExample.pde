/* Example for lookup tables (LUT).
 * Use the mouse wheel to switch the different tables/styles
 * and press the left mouse button to see the original image.
 *  
 * Author: Nick 'Milchreis' Müller
 */
 
import milchreis.imageprocessing.*;

PImage image;
LUT[] lookuptables;
int currentIndex = 0;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));

  // Create an array with all lookup-tables
  // LUT Styles:
  // RETRO, CONTRAST, CONTRAST_STRONG, ANALOG1, WINTER, SPRING, SUMMER, AUTUMN
  lookuptables = new LUT[LUT.STYLE.values().length];
  for(int i=0; i<lookuptables.length; i++) {
    lookuptables[i] = LUT.loadLut(LUT.STYLE.values()[i]);
  }
  
  // Load one style:
  // LUT style = LUT.loadLut(LUT.STYLE.ANALOG1);
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
  	image(LUT.apply(image, lookuptables[currentIndex]), 0, 0);
  
    fill(0);
    String stylename = LUT.STYLE.values()[currentIndex].name();
    text(stylename, width/2 - textWidth(stylename)/2, 30); 
  }
}

void mouseWheel(MouseEvent event) {
  currentIndex += 1;
  
  if(currentIndex >= lookuptables.length) {
    currentIndex = 0;
  }
}