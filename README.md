# Image processing for Processing
This library collects various image processing algorithms and provides a simple access to them. All algorithms are implemented in Java and runs without any other dependencies. If you need high power performance better use [opencv for processing](https://github.com/atduskgreg/opencv-processing).

# Examples
#### Original image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/examples/Basics/data/example.jpg?raw=true)

_Photo is taken by me [more here](https://www.instagram.com/milchreisjunkie/)_

#### Grayscale image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/gray.png?raw=true)
```
PImage processedImage = Grayscale.apply(image);
```

#### Threshold image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/threshold.png?raw=true)
```
PImage processedImage = Threshold.apply(image);  // Auto threshold
PImage processedImage = Threshold.apply(image, value);  // Threshold value between 0 and 255
```

#### Dilation image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/dilation.png?raw=true)
```
PImage processedImage =  Dilation.apply(image, radius);  // radius is a positive number
```

#### Erosion image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/erosion.png?raw=true)
```
PImage processedImage =  Erosion.apply(image, radius);  // radius is a positive number
```

#### AutoBalance image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/autobalance.png?raw=true)
```
PImage processedImage =  AutoBalance.apply(image);
```

#### Gaussian blur image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/gaussian.png?raw=true)
```
PImage processedImage = Gaussian.apply(image, 7, 0.84089642);   // kernel size and sigma 
```

#### Edge detection image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/canny.png?raw=true)
```
PImage processedImage = CannyEdgeDetector.apply(image);
```
 
#### Bloom image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/bloom.png?raw=true)
```
PImage processedImage = Bloom.apply(image, intensity);  // intensity between 0 and 255
```

#### Sharpen image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/sharpen.png?raw=true)
```
PImage processedImage = Sharpen.apply(image, sharpIntensity);  // sharpIntensity between 0.0 and 10.0
```

#### Lookup table image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/lut.png?raw=true)
```
LUT style = LUT.loadLut(LUT.STYLE.CONTRAST);
PImage processedImage = LUT.apply(image, style); 
```

# Special thanks
My special thanks goes to [avatarr](https://github.com/avatarr/java-image-processing-algorithm) for implementing and publishing basic algorithms. Also thank you very much Tom Gibara for your great blog post and the implementation of the [canny edge detector](http://www.tomgibara.com/computer-vision/canny-edge-detector).
