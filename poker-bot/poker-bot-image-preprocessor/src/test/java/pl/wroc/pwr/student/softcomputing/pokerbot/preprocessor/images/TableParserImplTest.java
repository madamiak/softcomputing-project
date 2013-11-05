package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;

public class TableParserImplTest {
	
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
		BufferedImage img= testObject.parseFoldButton();
		new ImageToFileSaver().save(img, "src/test/resources/Tournament/TestCrops/Fold_1.png");
	}

	@Test
	public void shouldCropOutFirstCard()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img= testObject.parseFirstCard();
		new ImageToFileSaver().save(img, "src/test/resources/Tournament/TestCrops/FirstCard_1.png");
	}

	@Test
	public void shouldCropOutSecondCard()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img= testObject.parseSecondCard();
		new ImageToFileSaver().save(img, "src/test/resources/Tournament/TestCrops/SecondCard_1.png");
	}

	@Test
	public void shouldCropOutFirstSuit()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img= testObject.parseFirstSuit();
		new ImageToFileSaver().save(img, "src/test/resources/Tournament/TestCrops/FirstSuit_1.png");
	}

	@Test
	public void shouldCropOutSecondSuit()
	{
		testObject.loadTable("src/test/resources/Tournament/1.png");
		BufferedImage img= testObject.parseSecondSuit();
		new ImageToFileSaver().save(img, "src/test/resources/Tournament/TestCrops/SecondSuit_1.png");
	}

	@Test
	public void shouldCropOutDealerButtonPositions()
	{
		for(int i=1;i<=8;i++){
			testObject.loadTable("src/test/resources/Tournament/"+i+".png");
			BufferedImage img= testObject.parseDealerButton();
			new ImageToFileSaver().save(img, "src/test/resources/Tournament/TestCrops/Dealer_"+i+".png");
		}
	}

}
