package pl.wroc.pwr.student.softcomputing.teacher.recognition.figures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.wroc.pwr.student.softcomputing.teacher.recognition.OutputConverter;

public class FigureOutputConverterTest {
	private OutputConverter testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new FigureOutputConverter();
	}

	@Test
	public void test() {
		assertStringResult("2",  toArray(0, 0, 0, 0));
		assertStringResult("3",  toArray(0, 0, 0, 1));
		assertStringResult("4",  toArray(0, 0, 1, 0));
		assertStringResult("5",  toArray(0, 0, 1, 1));
		assertStringResult("6",  toArray(0, 1, 0, 0));
		assertStringResult("7",  toArray(0, 1, 0, 1));
		assertStringResult("8",  toArray(0, 1, 1, 0));
		assertStringResult("9",  toArray(0, 1, 1, 1));
		assertStringResult("T", toArray(1, 0, 0, 0));
		assertStringResult("J",  toArray(1, 0, 0, 1));
		assertStringResult("Q",  toArray(1, 0, 1, 0));
		assertStringResult("K",  toArray(1, 0, 1, 1));
		assertStringResult("A",  toArray(1, 1, 0, 0));
	}

	private void assertStringResult(String expected, double[] array) {
		String result = testObject.convert(array);
		assertEquals(expected, result);
	}

	private double[] toArray(double... doubles) {
		return doubles;
	}
}
