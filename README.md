# Image processing for Processing
This library collects various image processing algorithms and provides a simple access to them. All algorithms are implemented in Java and runs without any other dependencies. If you need high power performance better use [opencv for processing](https://github.com/atduskgreg/opencv-processing).

# Installation
If you want to use this library in your processing sketch you can download it via the intergrated `Contribution Manager`. 
![alt install](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/install.png?raw=true)

# Examples
#### Original image
![alt original](https://github.com/Milchreis/processing-imageprocessing/blob/master/examples/Basics/data/example.jpg?raw=true)

_Photo is taken by me [more here](https://www.instagram.com/milchreisjunkie/)_

## Basics

#### Grayscale image
![alt grayscale](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/gray.png?raw=true)
```java
PImage processedImage = Grayscale.apply(image);
```

#### Flip image
![alt flip](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/flip.png?raw=true)
```java
PImage processedImage = Flip.apply(image, horizontal, vertical);  // horizontal and vertical are boolean
```

#### Invert colors
![alt invert colors](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/invertcolors.png?raw=true)
```java
// invertRed, invertGreen and invertBlue are boolean
PImage processedImage = InvertColors.apply(image, invertRed, invertGreen, invertBlue);  
```

#### Threshold image
![alt threshold](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/threshold.png?raw=true)
```java
PImage processedImage = Threshold.apply(image);  // Auto threshold
PImage processedImage = Threshold.apply(image, value);  // Threshold value between 0 and 255
```

#### Dilation image
![alt dilation](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/dilation.png?raw=true)
```java
PImage processedImage = Dilation.apply(image, radius);  // radius is a positive number
```

#### Erosion image
![alt erosion](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/erosion.png?raw=true)
```java
PImage processedImage = Erosion.apply(image, radius);  // radius is a positive number
```

#### Vignette image
![alt vignette](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/vignette.png?raw=true)
```java
// intensity and vignetteWidth are floats between 0.0 and 1.0
PImage processedImage = Vignette.apply(image, intensity, vignetteWidth); 
```

#### Quantization
![alt grayscale](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/quantization.png?raw=true)
```java
PImage processedImage = Quantization.apply(image, shades);  // shades is a positive number between 1 and 255
```

## Blur

#### Gaussian blur image
![alt gaussian](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/gaussian.png?raw=true)
```java
PImage processedImage = Gaussian.apply(image, 7, 0.84089642);   // kernel size and sigma 
```

#### Pixelize image
![alt pixelation](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/pixelation.png?raw=true)
```java
// pixelsize is a positive number
PImage processedImage = Pixelation.apply(image, pixelsize); 
// Pixelize a sub area of the input image
PImage processedImage = Pixelation.apply(image, pixelsize, subX, subY, subWidth, subHeight);  
```

#### Tilt-Shift-Effect
![alt tilt-shift](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/tiltshift.png?raw=true)
```java
PImage processedImage = TiltShift.apply(image, blurIntensity, horizontal, position, sharpWideness);   
```


## Edge detection

#### Canny's algorithm
![alt canny-edge](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/canny.png?raw=true)
```java
PImage processedImage = CannyEdgeDetector.apply(image);
```
 
#### Sobels algorithm
![alt sobel-edge](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/sobel.png?raw=true)
```java
PImage processedImage = SobelEdgeDetector.apply(image);
// for colored sobel (for each color channel)
PImage processedImage = SobelEdgeDetector.apply(image, false);
```
 
## Optimisation
 
#### Brightness
![alt autobalance](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/brightness.png?raw=true)
```java
PImage processedImage = Brightness.apply(image, value); 
// value isa positive number for brighting up or a negative for darken down
```

#### Changing highlights
![alt vignette](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/lights.png?raw=true)
```java
// intensity between -1.0 and 1.0
PImage processedImage = Lights.apply(image, intensity); 
```

#### Changing shadows
![alt vignette](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/shadows.png?raw=true)
```java
// intensity between -1.0 and 1.0
PImage processedImage = Shadows.apply(image, intensity); 
```

#### AutoBalance image
![alt autobalance](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/autobalance.png?raw=true)
```java
PImage processedImage = AutoBalance.apply(image);
```
 
#### Bloom image
![alt bloom](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/bloom.png?raw=true)
```java
PImage processedImage = Bloom.apply(image, intensity);  // intensity between 0 and 255
```

#### Sharpen image
![alt sharpen](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/sharpen.png?raw=true)
```java
PImage processedImage = Sharpen.apply(image, sharpIntensity);  // sharpIntensity between 0.0 and 10.0
```

#### Color shift image
![alt sharpen](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/colorshift.png?raw=true)
```java
// hue is a value between 0 and 360
// offset is the color range which is accepted (in hue range)
// shift is the number of the subtracted or added hue value
PImage processedImage = ColorShift.applyHue(image, hue, offset, shift);  // or short: ColorShift.apply(image, hue, offset, shift)
PImage processedImage = ColorShift.applySaturation(image, hue, offset, shift);
PImage processedImage = ColorShift.applyBrightness(image, hue, offset, shift);
```

## Looks

#### Lookup table image
![alt lookup-table](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/lut.png?raw=true)
```java
LUT style = LUT.loadLut(LUT.STYLE.CONTRAST);
PImage processedImage = LUT.apply(image, style); 
```

#### Glitch image
![alt lookup-table](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/glitch.png?raw=true)
```java
PImage processedImage = Glitch.apply(image, intensity, scanlineheight);
```

#### Dithering
![alt dithering](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/dithering_d1.png?raw=true)
```java
// default dithering algorithm is BAYER_4x4
PImage processedImage = Dithering.apply(image);
// change algrithm: BAYER_2x2, BAYER_4x4, BAYER_8x8
PImage processedImage = Dithering.apply(image, Dithering.Algorithm.BAYER_8x8);
// use a curstom kernel (kernel = float[])
PImage processedImage = Dithering.aapply(PImage image, kernel);  
```

#### Halftone image
![alt halftone](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/halftone.png?raw=true)
```java
PImage processedImage = Halftone.apply(image, dotsize);  // dot size in pixel
PImage processedImage = Halftone.apply(image, dotsize, grid); // grid = true, on false honeycomb style
PImage processedImage = Halftone.apply(image, dotsize, foreground, background);  // background and foreground colors
PImage processedImage = Halftone.apply(image, dotsize, foreground, background, grid);
PImage processedImage = Halftone.apply(image, dotsize, foreground, background, spacing, grid); // size between dots in pixels
```

#### ASCII image
![alt ascii](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/ASCII.png?raw=true)
```java
PImage processedImage = ASCII.apply(image);
// characterset = ASCII.SHORT_SET or ASCII.LONG_SET, another String from black to white
PImage processedImage = ASCII.apply(image, characterset); 
PImage processedImage = ASCII.apply(image, characterset, fontSize); // fontSize is an integer
PImage processedImage = ASCII.apply(image, characterset, fontSize, foregroundColor, backgroundColor, toneInColor);
```


## Miscellaneous

#### Stacked images
![alt stacked](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/stacker.png?raw=true)
```java
// Add so many images in the end as you need
PImage processedImage = Stacker.apply(Stacker.ALGORITHM.AVERAGE, image1, image2);
PImage processedImage = Stacker.apply(Stacker.ALGORITHM.MEDIAN, image1, image2);
```

#### Blending two images
![alt blending](https://github.com/Milchreis/processing-imageprocessing/blob/master/img/blend.png?raw=true)
```java
// intensity is a float between 0.0 and 1.0
PImage processedImage = Blend.apply(image1, image2, intensity);
```


# Special thanks
My special thanks goes to [avatarr](https://github.com/avatarr/java-image-processing-algorithm) for implementing and publishing basic algorithms. Also thank you very much Tom Gibara for your great blog post and the implementation of the [canny edge detector](http://www.tomgibara.com/computer-vision/canny-edge-detector).
