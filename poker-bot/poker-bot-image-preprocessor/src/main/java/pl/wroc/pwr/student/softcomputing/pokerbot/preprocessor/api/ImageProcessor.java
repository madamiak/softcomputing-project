package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

/**
 * @author RaV
 * 
 * Processing image.
 *
 */
public interface ImageProcessor {

	/**
	 * @param image
	 * 		Image to be processed.
	 * @return
	 * 		Input image converted to grayscale.
	 */
	BufferedImage convertToGrayscale(BufferedImage image);

	/**
	 * @param image
	 * 		Image to be processed.
	 * @return
	 * 		Input image converted to black and white.
	 */
	BufferedImage convertToBlackAndWhite(BufferedImage image);
	
	
}
