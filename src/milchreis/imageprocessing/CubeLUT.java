package milchreis.imageprocessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import milchreis.imageprocessing.utils.Tools;
import processing.core.PImage;

public class CubeLUT {
	

	public static Cube parse(File cubeFile) {
		
		Cube cube = new Cube();
		
		try {
			List<String> lines = Files.readAllLines(cubeFile.toPath());
			
			for(String line : lines) {
				
				line = line.trim();
				
				if(line.startsWith("#") || line.isEmpty()) {
					continue;
				}
				
				String[] parts = line.split("\\s+");
				
				if(parts[0].equals("TITLE")) {
					cube.title = parts[0];
					
				} else if(parts[0].equals("DOMAIN_MIN")) {
					
					cube.domainMin = new float[]{
							Float.parseFloat(parts[1]),
							Float.parseFloat(parts[2]),
							Float.parseFloat(parts[3])
					};
					
				} else if(parts[0].equals("DOMAIN_MAX")) {
					cube.domainMax = new float[]{
							Float.parseFloat(parts[1]),
							Float.parseFloat(parts[2]),
							Float.parseFloat(parts[3])
					};
					
				} else if(parts[0].equals("LUT_1D_SIZE")) {
					cube.type = "1D";
					cube.size = Integer.parseInt(parts[1]);

				} else if(parts[0].equals("LUT_3D_SIZE")) {
					cube.type = "3D";
					cube.size = Integer.parseInt(parts[1]);
					
				} else {
					cube.rgb.add(Float.parseFloat(parts[0]));
					cube.rgb.add(Float.parseFloat(parts[1]));
					cube.rgb.add(Float.parseFloat(parts[2]));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return cube;
	}
	
	public static PImage apply(PImage src, Cube lut) {
		
		PImage out = Tools.createBlankImageLike(src);
		
		for (int y = 0; y < src.height; y++) {
			for (int x = 0; x < src.width; x++) {
		
				int[] rgb = Tools.getColors(src, x, y);
				
				float ri = rgb[0] / 255.0f;
				float gi = rgb[1] / 255.0f;
				float bi = rgb[2] / 255.0f;
	
				// map to domain
				ri = (ri - lut.domainMin[0]) / (lut.domainMax[0] - lut.domainMin[0]);
				gi = (gi - lut.domainMin[1]) / (lut.domainMax[1] - lut.domainMin[1]);
				bi = (bi - lut.domainMin[2]) / (lut.domainMax[2] - lut.domainMin[2]);
	
				// map to grid units
				ri = ri * (float)(lut.size - 1);
				gi = gi * (float)(lut.size - 1);
				bi = bi * (float)(lut.size - 1);
	
				// clamp to grid bounds
				ri = ri < 0 ? 0 : (ri > (lut.size - 1) ? (lut.size - 1) : ri);
				gi = gi < 0 ? 0 : (gi > (lut.size - 1) ? (lut.size - 1) : gi);
				bi = bi < 0 ? 0 : (bi > (lut.size - 1) ? (lut.size - 1) : bi);

//				if(lut.type.equals("1D")) {
//			        int ro = interpolate3d(0, 1, lut.rgb.get(0));
//			        int go = interpolate3d(grid, gi, 1);
//			        int bo = interpolate3d(grid, bi, 2);
//			        Tools.set(out, x, y, ro, go, bo);
//			      
//				} else {
				
//					int ro = (int) (Interpolation.triLerp(bi, gi, ri, ) * 255.0);
//					int go = (int) (interpolate3d(lut, bi, gi, ri) * 255.0);
//					int bo = (int) (interpolate3d(lut, bi, gi, ri) * 255.0);
//					
//					PApplet.println(" -> " + ri + ", " + gi + ", " + bi);
//					Tools.set(out, x, y, ro, go, bo);
//				}
			
			}
		}
		
	    return out;
	}
	
		
}
