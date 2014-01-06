package pl.wroc.pwr.student.softcomputing.teacher.training.figures;

import java.util.HashMap;
import java.util.Map;

import pl.wroc.pwr.student.softcomputing.teacher.training.OutputConverter;

public class FigureOutputConverter implements OutputConverter {

	private final Map<String, double[]> arrays = new HashMap<>();

	public FigureOutputConverter() {
		arrays.put("2", new double[] { 0, 0, 0, 0 });
		arrays.put("3", new double[] { 0, 0, 0, 1 });
		arrays.put("4", new double[] { 0, 0, 1, 0 });
		arrays.put("5", new double[] { 0, 0, 1, 1 });
		arrays.put("6", new double[] { 0, 1, 0, 0 });
		arrays.put("7", new double[] { 0, 1, 0, 1 });
		arrays.put("8", new double[] { 0, 1, 1, 0 });
		arrays.put("9", new double[] { 0, 1, 1, 1 });
		arrays.put("T", new double[] { 1, 0, 0, 0 });
		arrays.put("J", new double[] { 1, 0, 0, 1 });
		arrays.put("Q", new double[] { 1, 0, 1, 0 });
		arrays.put("K", new double[] { 1, 0, 1, 1 });
		arrays.put("A", new double[] { 1, 1, 0, 0 });
	}

	@Override
	public double[] convert(String text) {
		return arrays.get(text);
	}

}
