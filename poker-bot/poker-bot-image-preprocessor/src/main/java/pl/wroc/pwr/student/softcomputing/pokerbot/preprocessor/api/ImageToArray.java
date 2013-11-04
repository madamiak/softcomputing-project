package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

/**
 * Provides functionality to convert image to double array.
 * 
 * @author RaV
 *
 */
public interface ImageToArray {

	
	/**
	 * @param image
	 * 			image as BufferedImage object.
	 * @return image as flattened array of doubles.
	 */
	double[] convert(BufferedImage image);
}
