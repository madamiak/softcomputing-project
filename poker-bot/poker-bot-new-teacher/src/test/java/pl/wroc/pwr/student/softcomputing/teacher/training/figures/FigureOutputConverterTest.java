package pl.wroc.pwr.student.softcomputing.teacher.training.figures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;

public class FigureOutputConverterTest {
	private static final double DELTA = 0.00001;
	private OutputConverter testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new FigureOutputConverter();
	}

	@Test
	public void shouldConvertOutputsProperly() {
		assertCorrespondingArray(arrayOf(0, 0, 0, 0), "2");
		assertCorrespondingArray(arrayOf(0, 0, 0, 1), "3");
		assertCorrespondingArray(arrayOf(0, 0, 1, 0), "4");
		assertCorrespondingArray(arrayOf(0, 0, 1, 1), "5");
		assertCorrespondingArray(arrayOf(0, 1, 0, 0), "6");
		assertCorrespondingArray(arrayOf(0, 1, 0, 1), "7");
		assertCorrespondingArray(arrayOf(0, 1, 1, 0), "8");
		assertCorrespondingArray(arrayOf(0, 1, 1, 1), "9");
		assertCorrespondingArray(arrayOf(1, 0, 0, 0), "T");
		assertCorrespondingArray(arrayOf(1, 0, 0, 1), "J");
		assertCorrespondingArray(arrayOf(1, 0, 1, 0), "Q");
		assertCorrespondingArray(arrayOf(1, 0, 1, 1), "K");
		assertCorrespondingArray(arrayOf(1, 1, 0, 0), "A");
	}

	private void assertCorrespondingArray(double[] expected, String input) {
		double[] result = testObject.convert(input);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(i + ". value is not correct", expected[i], result[i], DELTA);
		}
	}

	private double[] arrayOf(double... doubles) {
		return doubles;
	}

}
