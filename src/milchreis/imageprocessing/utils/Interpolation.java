package milchreis.imageprocessing.utils;

public class Interpolation {

	public static float lerp(float x, float x1, float x2, float q00, float q01) {
		return ((x2 - x) / (x2 - x1)) * q00 + ((x - x1) / (x2 - x1)) * q01;
	}

	public static float biLerp(float x, float y, float q11, float q12, float q21, float q22, float x1, float x2,
			float y1, float y2) {
		float r1 = lerp(x, x1, x2, q11, q21);
		float r2 = lerp(x, x1, x2, q12, q22);

		return lerp(y, y1, y2, r1, r2);
	}
	
	public static float triLerp(float x, float y, float z, float q000, float q001, float q010, float q011, float q100,
			float q101, float q110, float q111, float x1, float x2, float y1, float y2, float z1, float z2) {
		
		float x00 = lerp(x, x1, x2, q000, q100);
		float x10 = lerp(x, x1, x2, q010, q110);
		float x01 = lerp(x, x1, x2, q001, q101);
		float x11 = lerp(x, x1, x2, q011, q111);
		float r0 = lerp(y, y1, y2, x00, x01);
		float r1 = lerp(y, y1, y2, x10, x11);

		return lerp(z, z1, z2, r0, r1);
	}
	
	public static float triLerp(float x, float y, float z, float x1, float y1, float z1, float x2, float y2, float z2) {
		return triLerp(x, y, z, 1, 1, 1, 1, 1, 1, 1, 1, x1, x2, y1, y2, z1, z2);
	}
	
}
