package ie.gmit.dip;

/*
 * This enum contains all the available convolution kernels the app can use.
 * For localization purposes, each kernel does not know its name but it knows
 * where to find it in Strings.java
 */

public enum Kernel {
	IDENTITY(new double[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }, Strings.K_IDENTITY),
	EDGE_DETECTION_1(new double[][] { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } }, Strings.K_EDGE_DECECTION),
	EDGE_DETECTION_2(new double[][] { { 1, 0, -1 }, { 0, 0, 0 }, { -1, 0, 1 } }, Strings.K_EDGE_DECECTION_2),
	LAPLACIAN(new double[][] { { 0, -1, 0 }, { -1, 4, -1 }, { 0, -1, 0 } }, Strings.K_LAPLACIAN),
	SHARPEN(new double[][] { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } }, Strings.K_SHARPEN),
	VERTICAL_LINES(new double[][] { { -1, 2, -1 }, { -1, 2, -1 }, { -1, 2, -1 } }, Strings.K_VERTICAL_LINES),
	HORIZONTAL_LINES(new double[][] { { -1, -1, -1 }, { 2, 2, 2 }, { -1, -1, -1 } }, Strings.K_HORIZONTAL_LINES),
	DIAGONAL_45_LINES(new double[][] { { -1, -1, 2 }, { -1, 2, -1 }, { 2, -1, -1 } }, Strings.K_DIAGONAL_45_LINES),
	BOX_BLUR(new double[][] { { 0.111, 0.111, 0.111 }, { 0.111, 0.111, 0.111 }, { 0.111, 0.111, 0.111 } },
			Strings.K_BOX_BLUR),
	SOBEL_HORIZONTAL(new double[][] { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } }, Strings.K_SOBEL_HORIZONTAL),
	SOBEL_VERTICAL(new double[][] { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } }, Strings.K_SOBEL_VERTICAL);

	private final double[][] kernel;
	private final Strings name;

	Kernel(double[][] kernel, Strings name) {
		this.kernel = kernel;
		this.name = name;
	}

	public double[][] kernel() {
		return this.kernel;
	}

	@Override
	public String toString() {
		return this.name.get();
	}
}