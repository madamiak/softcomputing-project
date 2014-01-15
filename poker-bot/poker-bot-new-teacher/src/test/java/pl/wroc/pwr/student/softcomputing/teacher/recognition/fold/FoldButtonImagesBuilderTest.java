package pl.wroc.pwr.student.softcomputing.teacher.recognition.fold;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;

@RunWith(MockitoJUnitRunner.class)
public class FoldButtonImagesBuilderTest {
	private ImagesBuilder<BufferedImage, File> testObject;

	@Mock
	private TableParser tableParser;

	@Before
	public void setUp() throws Exception {
		testObject = new FoldButtonImagesBuilder(tableParser);
	}

	@Test
	public void test() {
		File file = new File("asdsad.png");
		BufferedImage bufferedImage = mock(BufferedImage.class);
		when(tableParser.parseFoldButton()).thenReturn(bufferedImage);
		Images<BufferedImage> result = testObject.buildFrom(file, null);
		verify(tableParser).loadTable(file.getAbsolutePath());
		verify(tableParser).parseFoldButton();
		assertEquals(bufferedImage, result.list().get(0).getData());
	}

}
