package pl.wroc.pwr.student.softcomputing.teacher.recognition.tablechips;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ChipsParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageProcessor;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.images.ImageProcessorImpl;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.tablechips.TableChipsRecognizer;

@RunWith(MockitoJUnitRunner.class)
public class TableChipsRecognizerTest {
	private Recognizer testObject;

	@Mock
	private ChipsParser chipsParser;

	@Mock
	private ImageProcessor imageProcessor;

	@Before
	public void setUp() throws Exception {
		testObject = new TableChipsRecognizer(chipsParser, imageProcessor);
	}

	@Test
	public void test() {
		Images images = mock(Images.class);
		Image image = mock(Image.class);
		BufferedImage bufferedImage = mock(BufferedImage.class);
		List list = new ArrayList<>();
		list.add(image);
		list.add(image);
		list.add(image);
		list.add(image);
		list.add(image);
		list.add(image);
		when(images.list()).thenReturn(list);
		when(image.getData()).thenReturn(bufferedImage);
		when(chipsParser.parseTableChips(bufferedImage)).thenReturn(100);
		when(imageProcessor.convertToBlackAndWhite(any(BufferedImage.class))).thenReturn(bufferedImage);
		
		Result result = testObject.recognize(images);
		
		verify(imageProcessor, times(6)).convertToBlackAndWhite(bufferedImage);
		verify(chipsParser, times(6)).parseTableChips(bufferedImage);
		assertEquals(result.getResultAsString(), "100,100,100,100,100,100");
	}

}
