package pl.wroc.pwr.student.softcomputing.teacher.training.suits;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;

public class SuitOutputConverterTest {
	private static final double DELTA = 0.00001;
	private OutputConverter testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new SuitOutputConverter();
	}

	@Test
	public void shouldConvertOutputsProperly() {
		assertCorrespondingArray(arrayOf(0, 0), "h");
		assertCorrespondingArray(arrayOf(0, 1), "d");
		assertCorrespondingArray(arrayOf(1, 0), "c");
		assertCorrespondingArray(arrayOf(1, 1), "s");
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
