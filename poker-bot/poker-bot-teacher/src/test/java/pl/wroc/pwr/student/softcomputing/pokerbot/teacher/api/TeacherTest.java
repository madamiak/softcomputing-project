package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;
import org.neuroph.nnet.learning.MomentumBackpropagation;

import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation.TeachingInteractor;

@RunWith(MockitoJUnitRunner.class)
public class TeacherTest {

	private Teacher testObject;

	@Mock
	private Request request;

	@Mock
	private NeuralNetwork neuralNetwork;

	@Test
	public void shouldDelegateTeachingToNeuralNetwork() throws Exception {
		DataSet toLearn = new DataSet(0);
		double[] params = new double[] { 0, 0, 0, 0 };
		when(request.neuralNetwork()).thenReturn(neuralNetwork);
		when(neuralNetwork.getLearningRule()).thenReturn(new MomentumBackpropagation());
		when(request.outputFile()).thenReturn("file.nnet");
		testObject = new TeachingInteractor(request);

		testObject.teach(toLearn, params);

		verify(neuralNetwork).learn(any(DataSet.class));
	}

	@Test
	public void shouldStoreOutputOfLearning() throws Exception {
		DataSet toLearn = new DataSet(0);
		double[] params = new double[] { 0, 0, 0, 0 };
		when(request.neuralNetwork()).thenReturn(neuralNetwork);
		when(neuralNetwork.getLearningRule()).thenReturn(new MomentumBackpropagation());
		when(request.outputFile()).thenReturn("file.nnet");
		testObject = new TeachingInteractor(request);

		testObject.teach(toLearn, params);

		verify(neuralNetwork).save(anyString());
	}
}
