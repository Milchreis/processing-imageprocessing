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
    int intensity = (int) map(mouseX, 0, width, 0, 255);
  	image(SimpleBloom.apply(image, intensity), 0, 0);
  }
}