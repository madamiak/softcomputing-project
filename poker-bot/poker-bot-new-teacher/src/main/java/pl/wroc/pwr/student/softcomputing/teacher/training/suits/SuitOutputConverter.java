package pl.wroc.pwr.student.softcomputing.teacher.training.suits;

import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;

public class SuitOutputConverter implements OutputConverter {

	private final Map<String, double[]> arrays = new HashMap<>();
	
	public SuitOutputConverter() {
		arrays.put("h", new double[] { 0, 0 });
		arrays.put("d", new double[] { 0, 1 });
		arrays.put("c", new double[] { 1, 0 });
		arrays.put("s", new double[] { 1, 1 });
	}
	
	@Override
	public double[] convert(String text) {
		return arrays.get(text);
	}

}
