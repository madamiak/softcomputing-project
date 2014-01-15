package pl.wroc.pwr.student.softcomputing.teacher.recognition.border;

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
public class BorderImagesBuilderTest {
	private ImagesBuilder<BufferedImage, File> testObject;
	
	@Mock
	private TableParser tableParser;
	
	@Before
	public void setUp() throws Exception {
		testObject = new BorderImagesBuilder(tableParser);
	}

	@Test
	public void shouldConvertFileIntoImages() {
		File file = new File("asd.png");
		BufferedImage image = mock(BufferedImage.class);
		when(tableParser.parseOpponentBorder(anyInt())).thenReturn(image);
		
		Images<BufferedImage> result = testObject.buildFrom(file , null);
		
		verify(tableParser).loadTable(file.getAbsolutePath());
		verify(tableParser, times(5)).parseOpponentBorder(anyInt());
		assertEquals(5, result.list().size());
	}

}
