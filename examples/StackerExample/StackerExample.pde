/* Example for stacking images.
 * Generates an average face by 10 example faces.
 * Normally the median pixel is selected. 
 * Use the mouse button to see the average mode. 
 * 
 * Author: Nick 'Milchreis' Müller
 */

import milchreis.imageprocessing.*;

PImage image;
PImage[] faces;

void setup() {
  size(128, 155);
  // Load faces grid
  image = loadImage(dataPath("yaleBfaces.jpg"));
  
  // Dimensions for each face
  int gridWidth = 128;
  int gridHeight = 155;
  
  // Create an array of images for each face
  faces = new PImage[(image.width/gridWidth) * (image.height/gridHeight)];
  int index = 0;
  
  for(int x=0; x < image.width; x+=gridWidth) {
    for(int y=0; y < image.height; y+=gridHeight) {
      faces[index++] = image.get(x, y, gridWidth, gridHeight);
    }
  }
}

void draw() {
  if(mousePressed == true) {
    // Alternative algorithm is average
    image(Stacker.apply(Stacker.ALGORITHM.AVERAGE, faces), 0, 0);
  } else {
    // Default algorithm is median
    image(Stacker.apply(faces), 0, 0);
  }
  
  // The array is not necessary. If you save your images in different variables use this:
  // Stacker.apply(image1, image2, image3);  // works also
}

