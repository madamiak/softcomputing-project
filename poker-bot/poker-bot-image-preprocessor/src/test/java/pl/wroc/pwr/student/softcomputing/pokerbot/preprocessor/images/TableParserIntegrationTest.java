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
	public void shouldCropOutOpponentsChips()
	{
		for (int i = 1; i <= 5; i++)
		{
			testObject.loadTable("src/test/resources/Tournament/5.png");
			BufferedImage img = testObject.parseOpponentChips(i);
			new ImageToFileSaver().save(img, "target/Opponent" + i + "Chips.png");
		}
	}

	@Test
	public void shouldCropOutOpponentsTableChips()
	{
		for (int i = 1; i <= 5; i++)
		{
			testObject.loadTable("src/test/resources/Tournament/14.png");
			BufferedImage img = testObject.parseOpponentTableChips(i);
			new ImageToFileSaver().save(img, "target/Opponent" + i + "TableChips14.png");
		}
        for (int i = 1; i <= 5; i++)
        {
            testObject.loadTable("src/test/resources/Tournament/4.png");
            BufferedImage img = testObject.parseOpponentTableChips(i);
            new ImageToFileSaver().save(img, "target/2Opponent" + i + "TableChips4.png");
        }
        for (int i = 1; i <= 5; i++)
        {
            testObject.loadTable("src/test/resources/Tournament/Fold20.png");
            BufferedImage img = testObject.parseOpponentTableChips(i);
            new ImageToFileSaver().save(img, "target/3Opponent" + i + "TableChips20.png");
        }
        for (int i = 1; i <= 5; i++)
        {
            testObject.loadTable("src/test/resources/Tournament/18.png");
            BufferedImage img = testObject.parseOpponentTableChips(i);
            new ImageToFileSaver().save(img, "target/18Opponent" + i + "TableChips18.png");
        }
	}

	@Test
	public void shouldCropOutPlayerChips()
	{
		testObject.loadTable("src/test/resources/Tournament/5.png");
		BufferedImage img = testObject.parsePlayerChips();
		new ImageToFileSaver().save(img, "target/PlayerChips.png");
	}

	@Test
	public void shouldCropOutPlayerTableChips()
	{
		testObject.loadTable("src/test/resources/Tournament/5.png");
		BufferedImage img = testObject.parsePlayerTableChips();
		new ImageToFileSaver().save(img, "target/PlayerTableChips.png");
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
			testObject.loadTable("src/test/resources/Borders/2.png");
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

    @Test
    public void shouldCroppOutTableCards() throws Exception {
        testObject.loadTable("src/test/resources/Tournament/20.png");
        BufferedImage img = testObject.parseTableCards();
        new ImageToFileSaver().save(img, "target/TableCards.png");
    }
}
