package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.FoldParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;

public class FoldParserIntegrationlTest {
	private FoldParser foldParser;

	@Before
	public void setUp() throws Exception {
		foldParser = new FoldParserImpl();
	}

	@Test
	public void shouldReturnTrueWhenFoldButtonIsPresent() {
		TableParser tableParser= new TableParserImpl();
		tableParser.loadTable("src/test/resources/Tournament/4.png");
		BufferedImage image = tableParser.parseFoldButton();
		assertTrue(foldParser.parseFoldButton(image));
	}
	
	@Test
	public void shouldReturnFalseWhenFoldButtonIsNotPresent() {
		TableParser tableParser= new TableParserImpl();
		tableParser.loadTable("src/test/resources/Tournament/5.png");
		BufferedImage image = tableParser.parseFoldButton();
		assertFalse(foldParser.parseFoldButton(image));
	}

}
