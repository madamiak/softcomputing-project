package pl.wroc.pwr.student.softcomputing.teacher.recognition.tablechips;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageLoader;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.tablechips.TableChipsImagesBuilder;

@RunWith(MockitoJUnitRunner.class)
public class TableChipsImagesBuilderTest {
	private ImagesBuilder<BufferedImage, File> testObject;
	
	@Mock
	private TableParser tableParser;
	
	@Before
	public void setUp() throws Exception {
		testObject = new TableChipsImagesBuilder(tableParser);
	}

	@Test
	public void shouldConvertFileIntoImages() {
		File file = new File("asd.png");
		BufferedImage image = mock(BufferedImage.class);
		when(tableParser.parsePlayerTableChips()).thenReturn(image);
		when(tableParser.parseOpponentTableChips(anyInt())).thenReturn(image);
		
		Images<BufferedImage> result = testObject.buildFrom(file , null);
		
		verify(tableParser).loadTable(file.getAbsolutePath());
		verify(tableParser).parsePlayerTableChips();
		verify(tableParser, times(5)).parseOpponentTableChips(anyInt());
		assertEquals(6, result.list().size());
	}

}
