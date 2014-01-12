package pl.wroc.pwr.student.softcomputing.teacher.recognition.suits;

import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.teacher.recognition.OutputConverter;

public class SuitOutputConverter implements OutputConverter {
	private static final double TRESHOLD = 0.5;
	private Map<String, String> strings = new HashMap<>();

	public SuitOutputConverter() {
		strings .put("00", "h");
		strings .put("01", "d");
		strings .put("10", "c");
		strings .put("11", "s");
	}

	@Override
	public String convert(double[] output) {
		String key = toString(output);
		return strings.get(key);
	}

	private String toString(double[] output) {
		StringBuilder toConvert = new StringBuilder();
		for (double d : output) {
			toConvert.append(d < TRESHOLD ? "0" : "1");
		}
		String key = new String(toConvert);
		return key;
	}

}
