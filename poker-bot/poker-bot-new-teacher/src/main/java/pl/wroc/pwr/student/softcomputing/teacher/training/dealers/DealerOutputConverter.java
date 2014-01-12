package pl.wroc.pwr.student.softcomputing.teacher.training.dealers;

import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;

public class DealerOutputConverter implements OutputConverter {

	private final Map<String, double[]> arrays = new HashMap<>();
	
	public DealerOutputConverter() {
		arrays.put("1", new double[] { 0, 0, 0 });
		arrays.put("2", new double[] { 0, 0, 1 });
		arrays.put("3", new double[] { 0, 1, 0 });
		arrays.put("4", new double[] { 0, 1, 1 });
		arrays.put("5", new double[] { 1, 0, 0 });
		arrays.put("6", new double[] { 1, 0, 1 });
	}

	@Override
	public double[] convert(String text) {
		return arrays.get(text);
	}

}
