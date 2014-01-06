package pl.wroc.pwr.student.softcomputing.teacher.training.figures;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.core.learning.LearningRule;

import pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api.ImageConverter;
import pl.wroc.pwr.student.softcomputing.teacher.api.Teacher;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.LearningConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.NeuralNetworkConfig;
import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImage;
import pl.wroc.pwr.student.softcomputing.teacher.training.TrainingImages;

@RunWith(MockitoJUnitRunner.class)
public class FigureTeacherTest {
	private Teacher testObject;
	@Mock
	private ImageConverter imageConverter;

	@Before
	public void setUp() throws Exception {
		testObject = new FigureTeacher(imageConverter);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenMaxIterationsValueIsNotPositive()
			throws Exception {
		setLearningConfig(0, 0.2, 0.05, 0.7, "asd.nnet");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenLearningRateIsNotPositive()
			throws Exception {
		setLearningConfig(100, 0, 0.1, 0.7, "asd.nnet");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenLearningRateIsBiggerThanOne()
			throws Exception {
		setLearningConfig(100, 1.1, 0.1, 0.7, "asd.nnet");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenErrorRateIsNotPositive()
			throws Exception {
		setLearningConfig(100, 0.1, 0.0, 0.7, "asd.nnet");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenErrorRateIsBiggerThanOne()
			throws Exception {
		setLearningConfig(100, 0.1, 1.1, 0.7, "asd.nnet");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenMomentumIsNotPositive()
			throws Exception {
		setLearningConfig(100, 0.1, 0.1, 0.0, "asd.nnet");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenMomentumIsBiggerThanOne()
			throws Exception {
		setLearningConfig(100, 0.1, 0.1, 1.7, "asd.nnet");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenOutputFileIsEmpty() throws Exception {
		setLearningConfig(100, 0.1, 0.1, 0.7, "");
	}

	@Test
	public void shouldDelegateTeachingProcessToNeuralNetwork() throws Exception {
		setLearningConfig(100, 0.1, 0.1, 0.7, "asd.nnet");
		Images<BufferedImage> images = new FigureImages();
		TrainingImage image = mock(TrainingImage.class);
		BufferedImage bufferedImage = mock(BufferedImage.class);
		double[] array = new double[] {};
		when(image.getData()).thenReturn(bufferedImage);
		when(image.getName()).thenReturn("A");
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
