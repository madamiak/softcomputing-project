package pl.wroc.pwr.student.softcomputing.teacher.recognition.figures;

import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.teacher.recognition.OutputConverter;

public class FigureOutputConverter implements OutputConverter {
	private static final double TRESHOLD = 0.5;
	private Map<String, String> strings = new HashMap<>();

	public FigureOutputConverter() {
		strings .put("0000", "2");
		strings .put("0001", "3");
		strings .put("0010", "4");
		strings .put("0011", "5");
		strings .put("0100", "6");
		strings .put("0101", "7");
		strings .put("0110", "8");
		strings .put("0111", "9");
		strings .put("1000", "10");
		strings .put("1001", "J");
		strings .put("1010", "Q");
		strings .put("1011", "K");
		strings .put("1100", "A");
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
