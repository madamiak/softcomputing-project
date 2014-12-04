package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

/**
 * Parsing table image for specyfic fragments.
 * 
 * @author RaV
 * 
 */
public interface TableParser {

	/**
	 * Loads table image for future parsing.
	 * 
	 * @param tableImage
	 *            Table image file location that will be parsed.
	 */
	void loadTable(String tableImageFile);

	/**
	 * Loads table image for future parsing.
	 * 
	 * @param image
	 *            Image that will be parsed.
	 */
	void loadTable(BufferedImage image);

	/**
	 * @return returns cropped image of where fold buttons should be.
	 */
	BufferedImage parseFoldButton();

	/**
	 * @return returns cropped image of where players' first card should be.
	 */
	BufferedImage parseFirstCard();

	/**
	 * @return returns cropped image of where players' second card should be.
	 */
	BufferedImage parseSecondCard();

	/**
	 * @return returns cropped image of where players' first card's suit should
	 *         be.
	 */
	BufferedImage parseFirstSuit();

	/**
	 * @return returns cropped image of where players' second card's suit should
	 *         be.
	 */
	BufferedImage parseSecondSuit();

	/**
	 * @return returns image combined of cropped places where dealer button can
	 *         appear.
	 */
	BufferedImage parseDealerButton();

	/**
	 * @param opponentNumber
	 *            index of opponent counting clockwise from player whose part
	 *            should be parsed.
	 * @return returns cropped image of place under opponents' nick, where
	 *         number his chips or ALL IN text appears.
	 */
	BufferedImage parseOpponentChips(int opponentNumber);

	/**
	 * @param opponentNumber
	 *            index of opponent counting clockwise from player whose part
	 *            should be parsed.
	 * @return returns cropped image of place on table where number of
	 *         opponents' his chips in play should appear.
	 */
	BufferedImage parseOpponentTableChips(int opponentNumber);

	/**
	 * @return returns cropped image of place under players' nick, where number
	 *         his chips.
	 */
	BufferedImage parsePlayerChips();

	/**
	 * @return returns cropped image of place on table where number of number
	 *         players' chips in play should appear.
	 */
	BufferedImage parsePlayerTableChips();

	/**
	 * @param opponentNumber
	 *            index of opponent counting clockwise from player whose part
	 *            should be parsed.
	 * @return returns cropped image of place on table where pair of small cards
	 *         should appear if opponent is still playing current hand.
	 */
	BufferedImage parseOpponentCards(int opponentNumber);

	/**
	 * @param opponentNumber
	 *            index of opponent counting clockwise from player whose part
	 *            should be parsed.
	 * @return return cropped image of part of opponents' border indicating his
	 *         'rank'.
	 */
	BufferedImage parseOpponentBorder(int opponentNumber);

    /**
     * @param opponentNumber
     *            index of opponent counting clockwise from player whose part
     *            should be parsed.
     * @return returns cropped image of table where players avatar should appear
     *         if he's still at the table.
     */
    BufferedImage parseOpponentAppereanceAtTable(int opponentNumber);

    BufferedImage parseTableCards();
}
