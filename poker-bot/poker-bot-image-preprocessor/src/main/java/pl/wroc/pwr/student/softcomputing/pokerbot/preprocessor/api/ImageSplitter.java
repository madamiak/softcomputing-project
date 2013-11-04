package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

/**
 * Provides image splitting functionality.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface ImageSplitter
{

	/**
	 * Crop image from existing one from specified point and specified height and
	 * width.
	 * 
	 * @param image
	 *          BufferedImage image object.
	 * @param coordinateX
	 *          Coordinate X from which the image will be cropped.
	 * @param coordinateY
	 *          Coordinate Y from which the image will be cropped.
	 * @param height
	 *          Height of cropped image.
	 * @param width
	 *          Width of cropped image.
	 */
	BufferedImage crop(BufferedImage image, int coordinateX, int coordinateY, int height, int width);

}
