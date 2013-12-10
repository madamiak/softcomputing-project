package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.DataSet;

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
		when(request.neuralNetwork()).thenReturn(neuralNetwork);
		when(request.outputFile()).thenReturn("file.nnet");
		testObject = new TeachingInteractor(request);

		testObject.teach(toLearn, null);

		verify(neuralNetwork).learn(any(DataSet.class));
	}

	@Test
	public void shouldStoreOutputOfLearning() throws Exception {
		DataSet toLearn = new DataSet(0);
		when(request.neuralNetwork()).thenReturn(neuralNetwork);
		when(request.outputFile()).thenReturn("file.nnet");
		testObject = new TeachingInteractor(request);

		testObject.teach(toLearn, null);

		verify(neuralNetwork).save(anyString());
	}
}
