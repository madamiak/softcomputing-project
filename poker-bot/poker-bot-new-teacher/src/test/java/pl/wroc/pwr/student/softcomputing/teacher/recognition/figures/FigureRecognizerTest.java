package pl.wroc.pwr.student.softcomputing.teacher.recognition.figures;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.neuroph.core.NeuralNetwork;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Result;

@RunWith(MockitoJUnitRunner.class)
public class FigureRecognizerTest {

	@Mock
	private ImageConverter imageConverter;

	@Mock
	private NeuralNetwork neuralNetwork;

	@InjectMocks 
	private FigureRecognizer testObject;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnResultOfRecognition() {
		Images images = mock(Images.class);
		Image image = mock(Image.class);
		List list = new ArrayList<>();
		list.add(image);
		when(images.list()).thenReturn(list );
		when(neuralNetwork.getOutput()).thenReturn(new double[] { 0, 0, 0, 0 });
		Result result = testObject.recognize(images);
		assertEquals("2", result.getResultAsString());
	}

}
