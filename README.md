# Image processing for Processing
This library collects various image processing algorithms and provides a simple access to them. All algorithms are implemented in Java and runs without any other dependencies. If you need high power performance better use [opencv for processing](https://github.com/atduskgreg/opencv-processing).

# Examples
#### Original image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/examples/Basics/data/example.jpg?raw=true)

_Photo is taken by me [more here](https://www.instagram.com/milchreisjunkie/)_

## Basics

#### Grayscale image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/gray.png?raw=true)
```java
PImage processedImage = Grayscale.apply(image);
```

#### Threshold image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/threshold.png?raw=true)
```java
PImage processedImage = Threshold.apply(image);  // Auto threshold
PImage processedImage = Threshold.apply(image, value);  // Threshold value between 0 and 255
```

#### Dilation image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/dilation.png?raw=true)
```java
PImage processedImage = Dilation.apply(image, radius);  // radius is a positive number
```

#### Erosion image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/erosion.png?raw=true)
```java
PImage processedImage = Erosion.apply(image, radius);  // radius is a positive number
```

## Blur

#### Gaussian blur image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/gaussian.png?raw=true)
```java
PImage processedImage = Gaussian.apply(image, 7, 0.84089642);   // kernel size and sigma 
```

#### Pixelize image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/pixelation.png?raw=true)
```java
// pixelsize is a positive number
PImage processedImage = Pixelation.apply(image, pixelsize); 
// Pixelize a sub area of the input image
PImage processedImage = Pixelation.apply(image, pixelsize, subX, subY, subWidth, subHeight);  
```

## Edge detection

#### Edge detection image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/canny.png?raw=true)
```java
PImage processedImage = CannyEdgeDetector.apply(image);
```
 
## Optimisation
 
#### AutoBalance image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/autobalance.png?raw=true)
```java
PImage processedImage = AutoBalance.apply(image);
```
 
#### Bloom image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/bloom.png?raw=true)
```java
PImage processedImage = Bloom.apply(image, intensity);  // intensity between 0 and 255
```

#### Sharpen image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/sharpen.png?raw=true)
```java
PImage processedImage = Sharpen.apply(image, sharpIntensity);  // sharpIntensity between 0.0 and 10.0
```

## Looks

#### Lookup table image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/lut.png?raw=true)
```java
LUT style = LUT.loadLut(LUT.STYLE.CONTRAST);
PImage processedImage = LUT.apply(image, style); 
```

#### Dithering
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/dithering_d1.png?raw=true)
```java
// default dithering algorithm is BAYER_4x4
PImage processedImage = Dithering.apply(image);
// change algrithm: BAYER_2x2, BAYER_4x4, BAYER_8x8
PImage processedImage = Dithering.apply(image, Dithering.Algorithm.BAYER_8x8);
// use a curstom kernel (kernel = float[])
PImage processedImage = Dithering.aapply(PImage image, kernel);  
```

#### Halftone image
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/halftone.png?raw=true)
```java
PImage processedImage = Halftone.apply(image, dotsize);  // dot size in pixel
PImage processedImage = Halftone.apply(image, dotsize, grid); // grid = true, on false honeycomb style
PImage processedImage = Halftone.apply(image, dotsize, foreground, background);  // background and foreground colors
PImage processedImage = Halftone.apply(image, dotsize, foreground, background, grid);
PImage processedImage = Halftone.apply(image, dotsize, foreground, background, spacing, grid); // size between dots in pixels
```

## Miscellaneous

#### Stacked images
![alt text](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/stacker.png?raw=true)
```java
// Add so many images in the end as you need
PImage processedImage = Stacker.apply(Stacker.ALGORITHM.AVERAGE, image1, image2);
PImage processedImage = Stacker.apply(Stacker.ALGORITHM.MEDIAN, image1, image2);
```


# Special thanks
My special thanks goes to [avatarr](https://github.com/avatarr/java-image-processing-algorithm) for implementing and publishing basic algorithms. Also thank you very much Tom Gibara for your great blog post and the implementation of the [canny edge detector](http://www.tomgibara.com/computer-vision/canny-edge-detector).
