package pl.wroc.pwr.student.softcomputing.teacher.recognition.dealers;

import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.teacher.recognition.OutputConverter;

public class DealerOutputConverter implements OutputConverter {
	private static final double TRESHOLD = 0.5;
	private Map<String, String> strings = new HashMap<>();

	public DealerOutputConverter() {
		strings.put("000", "1");
		strings.put("001", "2");
		strings.put("010", "3");
		strings.put("011", "4");
		strings.put("100", "5");
		strings.put("101", "6");
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
