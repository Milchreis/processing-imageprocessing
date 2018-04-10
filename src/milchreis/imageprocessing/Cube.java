package milchreis.imageprocessing;

import java.util.ArrayList;
import java.util.List;

public class Cube {
	String title;
	String type;
	int size = 0;
	float[] domainMin;
	float[] domainMax;
	List<Float> rgb = new ArrayList<>();
	
	
	public float get(int x, int y, int z) {
		return rgb.get((z * size * size) + (y * size) + x);
	}

}