package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.io.IOException;

/*
 * The Convolution class contains static methods to perform a convolution
 * between any kernel array from the Kernel enum and a BufferedImage object
 */

public class Convolution {
	/*
	 * Retrieve separate channel values from ARGB integer
	 */
	private static int getAlpha(int argb) {
		return ((argb >> 24) & 0xff);
	}

	private static int getRed(int argb) {
		return ((argb >> 16) & 0xff);
	}

	private static int getGreen(int argb) {
		return ((argb >> 8) & 0xff);
	}

	private static int getBlue(int argb) {
		return (argb & 0xff);
	}

	/*
	 * Retrieve ARGB integer from separate channel values
	 */
	private static int channelsToARGB(int alpha, int red, int green, int blue) {
		int argb = 0;
		argb = argb | (alpha << 24);
		argb = argb | (red << 16);
		argb = argb | (green << 8);
		argb = argb | blue;
		return argb;
	}

	/*
	 * Clamp integer to a range of 0..255
	 */
	private static int clamp(int value) {
		if (value < 0) {
			return 0;
		}
		if (value > 255) {
			return 255;
		}
		return value;
	}

	/*
	 * Apply a convolution kernel to a BufferedImage object
	 */
	public static void apply(ImageBuffer buffer, int kernelIndex) throws IOException {
		// Load the requested kernel and image
		double[][] kernel = Kernel.values()[kernelIndex].kernel();
		BufferedImage image = buffer.getImage();

		// Loop through all pixels in the image
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {

				// Preserve the alpha layer
				int alpha = getAlpha(image.getRGB(x, y));

				// Start with a black pixel
				int newRed = 0;
				int newGreen = 0;
				int newBlue = 0;

				// Loop through the kernel and get RGB values from adjacent pixels
				for (int row = 0; row < kernel.length; row++) {

					int nextY = y + row;
					// Vertical edge handling (extend the nearest border pixel)
					if (nextY < 0)
						nextY = 0;
					if (nextY >= image.getHeight())
						nextY = image.getHeight() - 1;

					for (int col = 0; col < kernel[0].length; col++) {
						try {

							int nextX = x + col;
							// Horizontal edge handling (extend the nearest border pixel)
							if (nextX < 0)
								nextX = 0;
							if (nextX >= image.getWidth())
								nextX = image.getWidth() - 1;

							// Multiply the next pixel's RGB values with the corresponding kernel element
							int neighbourARGB = image.getRGB(nextX, nextY);
							newRed += kernel[row][col] * getRed(neighbourARGB);
							newGreen += kernel[row][col] * getGreen(neighbourARGB);
							newBlue += kernel[row][col] * getBlue(neighbourARGB);

						} catch (Exception e) {
							continue;
						}
					}
				}

				// Make sure new RGB values are between 0 and 255
				newRed = clamp(newRed);
				newGreen = clamp(newGreen);
				newBlue = clamp(newBlue);

				// Apply the new RGB values to the buffered image
				image.setRGB(x, y, channelsToARGB(alpha, newRed, newGreen, newBlue));
			}
		}
		// Append the buffered image's file name
		buffer.appendFileName(Kernel.values()[kernelIndex].toString());
	}
}