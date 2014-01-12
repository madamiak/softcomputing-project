package pl.wroc.pwr.student.softcomputing.teacher.training.dealers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;

public class DealerOutputConverterTest {

	private static final double DELTA = 0.00001;
	private OutputConverter testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new DealerOutputConverter();
	}

	@Test
	public void shouldConvertOutputsProperly() {
		assertCorrespondingArray(arrayOf(0, 0, 0), "1");
		assertCorrespondingArray(arrayOf(0, 0, 1), "2");
		assertCorrespondingArray(arrayOf(0, 1, 0), "3");
		assertCorrespondingArray(arrayOf(0, 1, 1), "4");
		assertCorrespondingArray(arrayOf(1, 0, 0), "5");
		assertCorrespondingArray(arrayOf(1, 0, 1), "6");
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
