package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;

public class DemoIntegrationTest
{
	TableParser parser = new TableParserImpl();
	ImageConverter converter = new ImageToArrayConverter();
	ImageProcessor processor = new ImageProcessorImpl();
	ChipsParser chipParser = new ChipsParserImpl();

	@Before
	public void setUp()
	{
		parser.loadTable("src/test/resources/Tournament/1.png");
	}

	@Test
	public void showcase()
	{
		printConvertedFoldButton();
		printConvertedFirstCard();
		printConvertedFirstSuit();
		printConvertedSecondCard();
		printConvertedSecondSuit();
		printConvertedDealerButton();
		printConvertedPlayerChips();
		printConvertedPlayerTableChips();
		for (int i = 1; i < 6; i++)
		{
			printConvertedOpponentAppearance(i);
			printConvertedOpponentBorder(i);
			printConvertedOpponentCards(i);
			printConvertedOpponentChips(i);
			printConvertedOpponentTableChips(i);
		}
	}

	public void printConvertedOpponentTableChips(int i)
	{
		final BufferedImage opponentTableChipsImage = processor.convertToBlackAndWhite(parser.parseOpponentTableChips(i));
		final int chips = chipParser.parseTableChips(opponentTableChipsImage);
		System.out.println("OPPONENT " + i + " TABLE CHIPS = " + chips);
	}

	public void printConvertedOpponentChips(int i)
	{
		final BufferedImage opponentChipsImage = processor.convertToBlackAndWhite(parser.parseOpponentChips(i));
		final int chips = chipParser.parseChips(opponentChipsImage);
		System.out.println("OPPONENT " + i + " CHIPS = " + chips);
	}

	public void printConvertedOpponentCards(int i)
	{
		final BufferedImage opponentCardsImage = processor.scale(parser.parseOpponentCards(i), 0.5);
		final double[] convertedOpponentCards = converter.convert(opponentCardsImage);
		System.out.println("OPPONENT " + i + " CARDS (" + convertedOpponentCards.length / 3 + "):"
				+ Arrays.toString(convertedOpponentCards));
	}

	public void printConvertedOpponentBorder(int i)
	{
		final BufferedImage opponentBorderImage = parser.parseOpponentBorder(i);
		final double[] convertedOpponentBorderImage = converter.convert(opponentBorderImage);
		System.out.println("OPPONENT " + i + " BORDER (" + convertedOpponentBorderImage.length / 3 + "):"
				+ Arrays.toString(convertedOpponentBorderImage));
	}

	public void printConvertedOpponentAppearance(int i)
	{
		final BufferedImage opponentAppearanceImage = parser.parseOpponentAppereanceAtTable(i);
		final double[] convertedOpponentAppearance = converter.convert(opponentAppearanceImage);
		System.out.println("OPPONENT " + i + " APPEARANCE (" + convertedOpponentAppearance.length / 3 + "):"
				+ Arrays.toString(convertedOpponentAppearance));
	}

	public void printConvertedPlayerTableChips()
	{
		final BufferedImage playerTableChipsImage = processor.convertToBlackAndWhite(parser.parsePlayerTableChips());
		final int chips = chipParser.parseTableChips(playerTableChipsImage);
		System.out.println("PLAYER TABLE CHIPS = " + chips);
	}

	public void printConvertedPlayerChips()
	{
		final BufferedImage playerChipsImage = processor.convertToBlackAndWhite(parser.parsePlayerChips());
		final int chips = chipParser.parseChips(playerChipsImage);
		System.out.println("PLAYER CHIPS = " + chips);
	}

	public void printConvertedDealerButton()
	{
		final BufferedImage dealerButtonImage = processor.scale(parser.parseDealerButton(), 0.5);
		final double[] convertedDealerButtonImage = converter.convert(dealerButtonImage);
		System.out.println("DEALER (" + convertedDealerButtonImage.length / 3 + "):"
				+ Arrays.toString(convertedDealerButtonImage));
	}

	public void printConvertedSecondSuit()
	{
		final BufferedImage secondSuitImage = parser.parseSecondSuit();
		final double[] convertedSecondSuitImage = converter.convert(secondSuitImage);
		System.out.println("2ND SUIT (" + convertedSecondSuitImage.length / 3 + "):"
				+ Arrays.toString(convertedSecondSuitImage));
	}

	public void printConvertedSecondCard()
	{
		final BufferedImage secondCardImage = parser.parseSecondCard();
		final double[] convertedSecondCardImage = converter.convert(secondCardImage);
		System.out.println("2ND CARD (" + convertedSecondCardImage.length / 3 + "):"
				+ Arrays.toString(convertedSecondCardImage));
	}

	public void printConvertedFirstSuit()
	{
		final BufferedImage firstSuitImage = parser.parseFirstSuit();
		final double[] convertedFirstSuitImage = converter.convert(firstSuitImage);
		System.out.println("1ST SUIT (" + convertedFirstSuitImage.length / 3 + "):"
				+ Arrays.toString(convertedFirstSuitImage));
	}

	public void printConvertedFirstCard()
	{
		final BufferedImage firstCardImage = parser.parseFirstCard();
		final double[] convertedFirstCardImage = converter.convert(firstCardImage);
		System.out.println("1ST CARD (" + convertedFirstCardImage.length / 3 + "):"
				+ Arrays.toString(convertedFirstCardImage));
	}

	public void printConvertedFoldButton()
	{
		final BufferedImage foldButtonImage = processor.scale(parser.parseFoldButton(), 0.5);
		final double[] convertedFoldButtonImage = converter.convert(foldButtonImage);
		System.out.println("FOLD BUTTON (" + (convertedFoldButtonImage.length / 3) + "):"
				+ Arrays.toString(convertedFoldButtonImage));
	}

}
