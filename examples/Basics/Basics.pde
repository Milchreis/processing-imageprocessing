/* Example for basic image processing algorithms.
 * Use the mouse wheel to switch the different algorithms
 * and press the left mouse button to see the original image.
 *  
 * Author: Nick 'Milchreis' Müller
 */
 
import milchreis.imageprocessing.*;

int numberOfAlgorithms = 11;
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
    
    // Brightness correction
    if(currentAlgorithm == 4) {
      int intensity = (int) map(mouseX, 0, width, -255, 255);
      processedImage = Brightness.apply(image, intensity);
    }
    
    // AutoBalance for simple color correction
    if(currentAlgorithm == 5) {
      processedImage = AutoBalance.apply(image);
    }
    
    // Pixelation
    if(currentAlgorithm == 6) {
      int pixelsize = (int) map(mouseX, 0, width, 0, 100);
      processedImage = Pixelation.apply(image, pixelsize);
    }
    
    // Gaussian for blurred images 
    if(currentAlgorithm == 7) {
      processedImage = Gaussian.apply(image, 7, 0.84089642);
    }
    
    // Edge detection with Canny's algorithm
    if(currentAlgorithm == 8) {
      processedImage = CannyEdgeDetector.apply(image);
    }
    
    // Edge detection with Sobel's algorithm
    if(currentAlgorithm == 9) {
      // SobelEdgeDetector.apply(image, false) creates a colored image
      processedImage = SobelEdgeDetector.apply(image);
    }
    
    // Quantize the colors
    if(currentAlgorithm == 10) {
      int quant = (int) map(mouseX, 0, width, 1, 10);
      processedImage = Quantization.apply(image, quant);
    }
  }
  
  // show image
  image(processedImage, 0, 0);  
}

void mouseWheel(MouseEvent event) {
  currentAlgorithm += event.getCount();
  
  if(currentAlgorithm >= numberOfAlgorithms) {
    currentAlgorithm = 0;
  }
  if(currentAlgorithm < 0) {
    currentAlgorithm = numberOfAlgorithms-1;
  }
}