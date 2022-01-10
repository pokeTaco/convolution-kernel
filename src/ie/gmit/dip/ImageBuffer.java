package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

/*
 * The ImageBuffer class serves as a container for a BufferedImage object and its file name
 */

public class ImageBuffer {
	/*
	 * Instance variables and required getters/setters
	 */
	private BufferedImage image = null;
	private String fileName = null;

	public BufferedImage getImage() {
		return image;
	}

	/*
	 * Append the file name by a suffix. The output name will be the original file
	 * name followed by the app name, the name of the applied filter, and a time
	 * stamp to avoid accidentally overwriting other images in the folder. The name
	 * of the kernel is provided by the convolution method after applying the filter
	 */
	public void appendFileName(String filterName) {

		/*
		 * I researched the most concise and modern way of getting an easily readable
		 * time stamp online. The following two lines of code were originally found
		 * here:
		 * https://stackoverflow.com/questions/23068676/how-to-get-current-timestamp-in-
		 * string-format-in-java-yyyy-mm-dd-hh-mm-ss
		 * 
		 * The date format itself could potentially be localized, so it has been added
		 * to the Strings class
		 */
		String date = ZonedDateTime.now(ZoneId.systemDefault())
				.format(DateTimeFormatter.ofPattern(Strings.DATE_FORMAT.get()));

		/*
		 * If the buffered image has had at least one filter applied, its name will
		 * already have a suffix. Remove it to avoid very long and ugly file names
		 */
		int fileBaseNameEnd;
		if (fileName.indexOf("_" + Strings.APP_NAME.get()) == -1) {
			fileBaseNameEnd = fileName.lastIndexOf('.');
		} else {
			fileBaseNameEnd = fileName.indexOf("_" + Strings.APP_NAME.get());
		}
		String fileBaseName = fileName.substring(0, fileBaseNameEnd);
		fileName = fileBaseName + "_" + Strings.APP_NAME.get() + "_" + filterName + date + ".png";
	}

	/*
	 * Write the buffer content to the disk as a PNG
	 */
	public void write() throws IOException {
		ImageIO.write(image, "png", new File(fileName));
	}

	@Override
	/*
	 * Return some useful string representation
	 */
	public String toString() {
		return "  " + ConsoleFX.cBlue(Strings.BUFFERED_IMAGE.get()) + fileName + "\n\n  "
				+ ConsoleFX.cBlue(Strings.PROPERTIES.get()) + image + "\n";
	}

	/*
	 * Constructor
	 */
	public ImageBuffer(String fileName) throws IOException {
		this.fileName = fileName;
		this.image = ImageIO.read(new File(fileName));
	}
}