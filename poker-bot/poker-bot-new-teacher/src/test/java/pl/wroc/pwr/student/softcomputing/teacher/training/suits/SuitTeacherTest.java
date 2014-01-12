package pl.wroc.pwr.student.softcomputing.teacher.training.suits;

import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.NeuralNetworkConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImage;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImages;

@RunWith(MockitoJUnitRunner.class)
public class SuitTeacherTest {
	private SuitTeacher testObject;
	
	@Mock
	private ImageConverter imageConverter;

	@Before
	public void setUp() throws Exception {
		testObject = new SuitTeacher(imageConverter);
	}

	@Test
	public void shouldDelegateTeachingProcessToNeuralNetwork() {
		setLearningConfig(100, 0.1, 0.1, 0.7, "asd.nnet");
		Images<BufferedImage> images = new TrainingImages();
		TrainingImage image = mock(TrainingImage.class);
		BufferedImage bufferedImage = mock(BufferedImage.class);
		double[] array = new double[] {};
		when(image.getData()).thenReturn(bufferedImage);
		when(image.getName()).thenReturn("h");
		when(imageConverter.convert(any(BufferedImage.class)))
				.thenReturn(array);
		images.add(image);

		testObject.teach(images);

		verify(image, times(2)).getData();
		verify(imageConverter, times(2)).convert(bufferedImage);
	}

	private void setLearningConfig(int maxIterations, double learningRate,
			double errorRate, double momentum, String outputName) {
		LearningConfig learningConfig = new NeuralNetworkConfig(maxIterations,
				learningRate, errorRate, momentum, outputName);
		testObject.setLearningConfig(learningConfig);
	}

}
