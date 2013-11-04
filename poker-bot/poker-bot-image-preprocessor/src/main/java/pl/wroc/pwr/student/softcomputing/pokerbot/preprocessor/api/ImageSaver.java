package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

/**
 * Provides image splitting functionality.
 * 
 * @author Mateusz Adamiak
 * 
 */
public interface ImageSaver
{

	/**
	 * Crop image from existing one from specified point and specified height and
	 * width.
	 * 
	 * @param image
	 *          BufferedImage image object.
	 * @param pathToNewImage
	 *          Path to the save image.
	 */
	void save(BufferedImage image, String pathToNewImage);

}
