package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;

public class TableParserIntegrationTest
{

	private TableParser testObject;

	@Before
	public void setUp()
	{
		testObject = new TableParserImpl();
	}

	@Test
	public void shouldCropOutFoldButton()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img = testObject.parseFoldButton();
		new ImageToFileSaver().save(img, "target/Fold_1.png");
	}

	@Test
	public void shouldCropOutFirstCard()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img = testObject.parseFirstCard();
		new ImageToFileSaver().save(img, "target/FirstCard_1.png");
	}

	@Test
	public void shouldCropOutSecondCard()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img = testObject.parseSecondCard();
		new ImageToFileSaver().save(img, "target/SecondCard_1.png");
	}

	@Test
	public void shouldCropOutFirstSuit()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img = testObject.parseFirstSuit();
		new ImageToFileSaver().save(img, "target/FirstSuit_1.png");
	}

	@Test
	public void shouldCropOutSecondSuit()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img = testObject.parseSecondSuit();
		new ImageToFileSaver().save(img, "target/SecondSuit_1.png");
	}

	@Test
	public void shouldCropOutDealerButtonPositions()
	{
		for (int i = 1; i <= 8; i++)
		{
			testObject.loadTable("src/test/resources/Tournament/" + i + ".png");
			BufferedImage img = testObject.parseDealerButton();
			new ImageToFileSaver().save(img, "target/Dealer_" + i + ".png");
		}
	}

	@Test
	public void shouldCropOutOpponentsCards()
	{
		for (int i = 1; i <= 5; i++)
		{
			testObject.loadTable("src/test/resources/Tournament/1.png");
			BufferedImage img = testObject.parseOpponentCards(i);
			new ImageToFileSaver().save(img, "target/Opponent" + i + "Cards.png");
		}
	}

	@Test
	public void shouldCropOutOpponentsBorder()
	{
		for (int i = 1; i <= 5; i++)
		{
			testObject.loadTable("src/test/resources/Tournament/1.png");
			BufferedImage img = testObject.parseOpponentBorder(i);
			new ImageToFileSaver().save(img, "target/Opponent" + i + "Border.png");
		}
	}

	@Test
	public void shouldCropOutOpponentsAppereance()
	{
		for (int i = 1; i <= 5; i++)
		{
			testObject.loadTable("src/test/resources/Tournament/1.png");
			BufferedImage img = testObject.parseOpponentAppereanceAtTable(i);
			new ImageToFileSaver().save(img, "target/Opponent" + i + "Appereance.png");
		}
	}

}
