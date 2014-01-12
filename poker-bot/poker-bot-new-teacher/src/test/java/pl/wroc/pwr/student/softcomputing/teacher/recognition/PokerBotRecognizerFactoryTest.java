package pl.wroc.pwr.student.softcomputing.teacher.recognition;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.teacher.api.RecognizerFactory;
import pl.wroc.pwr.student.softcomputing.teacher.recognition.figures.FigureRecognizer;

public class PokerBotRecognizerFactoryTest {
	private RecognizerFactory testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new PokerBotRecognizerFactory();
	}

	@Test
	public void shouldCreateProperRecognizerInstance()
			throws InstantiationException {
		assertRecognizerType(FigureRecognizer.class, "figure");
	}

	@Test(expected = InstantiationException.class)
	public void shouldThrowExceptionWhenGivenTypeIsNotCorrect()
			throws Exception {
		testObject.create("");
	}

	private void assertRecognizerType(Class<?> expectedClass, String type)
			throws InstantiationException {
		assertEquals(expectedClass, testObject.create(type).getClass());
	}

}
