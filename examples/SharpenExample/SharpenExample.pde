import milchreis.imageprocessing.*;

PImage image;
float sharpIntensity = 0;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  if(mousePressed == true) {
    image(image, 0, 0);
  } else {
  	image(Sharpen.apply(image, sharpIntensity), 0, 0);
  }
  
  if(sharpIntensity > 6)
    sharpIntensity = 0;
  else 
    sharpIntensity += 0.05f;
}