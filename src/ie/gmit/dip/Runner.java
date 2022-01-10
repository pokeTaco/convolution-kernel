package ie.gmit.dip;

import java.io.IOException;

public class Runner {
	/*
	 * Instance variables: The app knows about one image buffer and one kernel at a
	 * time. The kernel integer refers to the index of the selected kernel in the
	 * Kernel.values() array. Booleans are used to keep track of events and inform
	 * the Menu class about them
	 */
	private static ImageBuffer buffer = null;
	private static int kernel = -1;

	private static boolean isQuitting = false;
	private static boolean isReadError = false;
	private static boolean isMissingSelection = false;
	private static boolean isWriteError = false;
	private static boolean isReadSuccess = false;
	private static boolean isWriteSuccess = false;
	private static boolean isConvolutionError = false;

	/*
	 * End the while loop that keeps the app running
	 */
	public static void quit() {
		isQuitting = true;
	}

	/*
	 * Select a kernel via its index in the Kernel.values() array
	 */
	public static void setKernel(int index) {
		kernel = index;
	}

	/*
	 * Buffer the image with the given file name. If the buffer already contains an
	 * image, overwrite it. In case of any errors, leave the buffer untouched and
	 * raise the appropriate flag
	 */
	public static void loadImage(String fileName) {
		try {
			buffer = new ImageBuffer(fileName);
		} catch (Exception e) {
			isReadError = true;
		}
		if (!isReadError) {
			isReadSuccess = true;
		}
	}

	/*
	 * If the buffer holds an image and the user has selected a kernel, request
	 * convolution. In case of any errors, raise the appropriate flag
	 */
	public static void applyFilter() {
		if (buffer != null && kernel > -1) {
			try {
				// Attempt convolution
				Convolution.apply(buffer, kernel);
				// If convolution succeeded:
				try {
					// Attempt to write the buffer content to the disk
					buffer.write();
					isWriteSuccess = true;
				} catch (IOException e) {
					isWriteError = true;
				}
			} catch (IOException e) {
				isConvolutionError = true;
			}
		} else {
			isMissingSelection = true;
		}
	}

	/*
	 * While the app is not shutting down, go through your instance variables to
	 * gather information to hand on to the Menu class
	 */
	public static void main(String[] args) {
		while (!isQuitting) {

			/*
			 * First, deal with any events (i.e. check and reset the boolean flags). What
			 * has happened since the user saw the menu the last time? There is a
			 * pre-defined string for each event
			 */
			String currentEvent = null;
			if (isReadError) {
				currentEvent = ConsoleFX.cRed(Strings.ERROR_BAD_FILE.get());
				isReadError = false;
			} else

			if (isMissingSelection) {
				if (buffer == null) {
					currentEvent = ConsoleFX.cRed(Strings.ERROR_BUFFER_EMPTY.get());
				} else if (kernel == -1) {
					currentEvent = ConsoleFX.cRed(Strings.ERROR_NO_FILTER.get());
				}
				isMissingSelection = false;
			} else

			if (isConvolutionError) {
				currentEvent = ConsoleFX.cRed(Strings.ERROR_CONVOLUTION_FAIL.get());
				isConvolutionError = false;
			} else

			if (isWriteError) {
				currentEvent = ConsoleFX.cRed(Strings.ERROR_WRITING_FILE.get());
				isWriteError = false;
			} else

			if (isWriteSuccess) {
				currentEvent = ConsoleFX.cGreen(Strings.SUCCESS_FILTERED_IMAGE_SAVED.get());
				isWriteSuccess = false;
			} else

			if (isReadSuccess) {
				currentEvent = ConsoleFX.cGreen(Strings.SUCCESS_IMAGE_BUFFERED.get());
				isReadSuccess = false;
			}

			/*
			 * Then, describe the buffer content and the selected kernel
			 */

			String currentBuffer = "none";
			if (buffer != null) {
				currentBuffer = buffer.toString();
			}

			String currentKernel = "none";
			if (kernel != -1) {
				currentKernel = "  " + ConsoleFX.cBlue(Strings.SELECTED_FILTER.get())
						+ Kernel.values()[kernel].toString();
			}

			/*
			 * Finally, pass on the gathered information to the Menu class
			 */
			Menu.show(currentEvent, currentBuffer, currentKernel);
		}
		System.out.println(Strings.SHUTDOWN.get());
	}
}