package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

/**
 * @author RaV
 * 
 * 	Parsing chips from image to int value.
 *
 */
public interface ChipsParser {

	/**
	 * @param image
	 * 	Image of chips under player/opponent avatar cropped by TableParserImpl.
	 * @return
	 * 	Int value of chips number from image.
	 */
	int parseChips(BufferedImage image);

	/**
	 * @param image
	 * 	Image of player/opponent chips on table cropped by TableParserImpl.
	 * @return
	 * 	Int value of chips number from image.
	 */
	int parseTableChips(BufferedImage image);

}
