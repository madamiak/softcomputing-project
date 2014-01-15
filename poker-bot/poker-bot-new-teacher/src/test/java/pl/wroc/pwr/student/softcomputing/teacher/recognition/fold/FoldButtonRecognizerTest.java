package pl.wroc.pwr.student.softcomputing.teacher.recognition.fold;

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

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.FoldParser;
import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.TableParser;
import pl.wroc.pwr.student.softcomputing.teacher.api.Recognizer;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;

@RunWith(MockitoJUnitRunner.class)
public class FoldButtonRecognizerTest {
	private Recognizer testObject;
	
	@Mock
	private FoldParser foldParser;

	@Before
	public void setUp() throws Exception {
		testObject = new FoldButtonRecognizer(foldParser);
	}

	@Test
	public void test() {
		Images images = mock(Images.class);
		Image image = mock(Image.class);
		BufferedImage bufferedImage = mock(BufferedImage.class);
		List list = new ArrayList<>();
		list.add(image);
		when(image.getData()).thenReturn(bufferedImage);
		when(images.list()).thenReturn(list);
		when(foldParser.parseFoldButton(any(BufferedImage.class))).thenReturn(true);
		
		Result result = testObject.recognize(images);
		
		verify(foldParser).parseFoldButton(bufferedImage);
		assertEquals("active", result.getResultAsString());
	}

}
