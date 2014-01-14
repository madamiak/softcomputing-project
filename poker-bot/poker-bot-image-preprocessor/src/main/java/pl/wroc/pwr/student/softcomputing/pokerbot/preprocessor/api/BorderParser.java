package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

/**
 * @author RaV
 * 
 * 	Parsing chips from image to int value.
 *
 */
public interface BorderParser {

	/**
	 * @param image
	 * 	Image of player/opponent border cropped by TableParserImpl.
	 * @return
	 * 	String describing border color.
	 */
	String parseBorder(BufferedImage image);
}
