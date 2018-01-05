import milchreis.imageprocessing.*;

int numberOfAlgorithms = 7;
int currentAlgorithm = 0;

PImage image;

void setup() {
  size(550, 550);
  // Load image
  image = loadImage(dataPath("example.jpg"));
}

void draw() {
  
  PImage processedImage = null;
  
  if(mousePressed == true) {
    processedImage = image;
  } else {
    
    // Grayscale converts the image in to 256 shades of gray
    if(currentAlgorithm == 0) {
      processedImage = Grayscale.apply(image);
    }
    
    // Threshold converts a pixel in bright or dark for a specific color value 
    if(currentAlgorithm == 1) {
      // automatic
      // processedImage = Threshold.apply(image);  
      
      // by mouseX
      processedImage = Threshold.apply(image, map(mouseX, 0, width, 0, 255));  
    }
        
    // Dilation expands the white regions by a radius
    // works best with threshold/binary images
    if(currentAlgorithm == 2) {
      processedImage = Threshold.apply(image); 
      processedImage = Dilation.apply(processedImage, (int) map(mouseX, 0, width, 1, 10));
    }
    
    // Erosion expands the dark regions by a radius
    // works best with threshold/binary images
    if(currentAlgorithm == 3) {
      processedImage = Threshold.apply(image); 
      processedImage = Erosion.apply(processedImage, (int) map(mouseX, 0, width, 1, 10));
    }
    
    // AutoBalance for simple color correction
    if(currentAlgorithm == 4) {
      processedImage = AutoBalance.apply(image);
    }
    
    // Gaussian for blurred images 
    if(currentAlgorithm == 5) {
      processedImage = Gaussian.apply(image, 7, 0.84089642);
    }
    
    // Edge detection with Canny's algorithm
    if(currentAlgorithm == 6) {
      processedImage = CannyEdgeDetector.apply(image);
    }
  }
  
  // show image
  image(processedImage, 0, 0);  
}

void mouseWheel(MouseEvent event) {
  currentAlgorithm += 1;
  
  if(currentAlgorithm >= numberOfAlgorithms) {
    currentAlgorithm = 0;
  }
}
