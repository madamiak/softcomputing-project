package pl.wroc.pwr.student.softcomputing.teacher.training.suits;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.ImagesBuilder;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImageConfig;

@RunWith(MockitoJUnitRunner.class)
public class SuitImagesBuilderTest {
	private ImagesBuilder<BufferedImage, List<File>> testObject;
	@Mock
	private TableParser tableParser;
	@Mock
	private ImageProcessor imageProcessor;

	@Before
	public void setUp() throws Exception {
		testObject = new SuitImagesBuilder(tableParser, imageProcessor);
	}

	@Test
	public void shouldTakeFiguresImagesFromParser() {
		List<File> imageFiles = new ArrayList<>();
		File imageFile = new File("asda.png");
		imageFiles.add(imageFile);
		ImageConfig imageConfig = new TrainingImageConfig(0.1, true, true);
		BufferedImage card = mock(BufferedImage.class);
		when(tableParser.parseFirstSuit()).thenReturn(card);
		when(tableParser.parseSecondSuit()).thenReturn(card);
		when(imageProcessor.scale(any(BufferedImage.class), anyDouble())).thenReturn(card);
		when(imageProcessor.convertToBlackAndWhite(any(BufferedImage.class))).thenReturn(card);
		
		Images<BufferedImage> images = testObject.buildFrom(imageFiles, imageConfig);
		
		verify(tableParser).loadTable(imageFile.getAbsolutePath());
		verify(tableParser).parseFirstSuit();
		verify(tableParser).parseSecondSuit();
		verify(imageProcessor, times(2)).scale(card, 0.1);
		verify(imageProcessor, times(2)).convertToBlackAndWhite(card);
		verify(imageProcessor, times(2)).convertToGrayscale(card);
		assertEquals(2, images.list().size());
	}
	
	@Test
	public void shouldTakeFiguresImagesFromParserAnotherScenario() throws Exception {
		List<File> imageFiles = new ArrayList<>();
		File imageFile1 = new File("asda.png");
		File imageFile2 = new File("asdasdasd.png");
		imageFiles.add(imageFile1);
		imageFiles.add(imageFile2);
		ImageConfig imageConfig = new TrainingImageConfig(0.1, false, true);
		BufferedImage card = mock(BufferedImage.class);
		when(tableParser.parseFirstSuit()).thenReturn(card);
		when(tableParser.parseSecondSuit()).thenReturn(card);
		when(imageProcessor.scale(any(BufferedImage.class), anyDouble())).thenReturn(card);
		
		Images<BufferedImage> images = testObject.buildFrom(imageFiles, imageConfig);
		
		verify(tableParser).loadTable(imageFile1.getAbsolutePath());
		verify(tableParser).loadTable(imageFile2.getAbsolutePath());
		verify(tableParser, times(2)).parseFirstSuit();
		verify(tableParser, times(2)).parseSecondSuit();
		verify(imageProcessor, times(4)).scale(card, 0.1);
		verify(imageProcessor, times(4)).convertToGrayscale(card);
		assertEquals(4, images.list().size());
	}
	
}
