/* Example for an ASCII-Art effect
 * Move the mouse from left to right to change the font size
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

  // Get the image as ascii text
  PImage smallImage = image.copy();
  smallImage.resize(80, 0);
  println(ASCII.getAsciiText(smallImage));
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
    
    int fontSize = (int) map(mouseX, 0, width, 8, 128);
    int foregroundColor = 0;
    int backgroundColor = 255;
    boolean toneInColor = true;
    PImage asciiImage = ASCII.apply(image, ASCII.SHORT_SET, fontSize, foregroundColor, backgroundColor, toneInColor);
     
  	image(asciiImage, 0, 0);
  	
  	// You can also use:
  	// ASCII.apply(PImage input);
  	// ASCII.apply(PImage input, String characterset); 
  	// ASCII.apply(PImage input, String characterset, int fontSize); 
  }
}