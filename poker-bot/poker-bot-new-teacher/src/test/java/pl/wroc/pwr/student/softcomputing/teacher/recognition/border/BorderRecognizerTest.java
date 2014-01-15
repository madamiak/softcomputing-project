package pl.wroc.pwr.student.softcomputing.teacher.recognition.border;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.BorderParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;

@RunWith(MockitoJUnitRunner.class)
public class BorderRecognizerTest {
	private Recognizer testObject;

	@Mock
	private BorderParser borderParser;

	@Before
	public void setUp() throws Exception {
		testObject = new BorderRecognizer(borderParser);
	}

	@Test
	public void test() {
		Images images = mock(Images.class);
		BufferedImage bufferedImage = mock(BufferedImage.class);
		Image image = mock(Image.class);
		List list = new ArrayList<>();
		list.add(image);
		list.add(image);
		list.add(image);
		list.add(image);
		list.add(image);
		when(images.list()).thenReturn(list);
		when(image.getData()).thenReturn(bufferedImage);
		when(borderParser.parseBorder(any(BufferedImage.class))).thenReturn("a");
		
		Result result = testObject.recognize(images);
		
		verify(borderParser, times(5)).parseBorder(bufferedImage);
		assertEquals("a,a,a,a,a", result.getResultAsString());
	}

}
