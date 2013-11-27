package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation;

import java.util.Arrays;

public class DataRow {

	private final double[] input;
	private final double[] output;

	public DataRow(double[] input, double[] output) {
		this.input = input;
		this.output = output;
	}

	public double[] getInput() {
		return input;
	}

	public double[] getOutput() {
		return output;
	}

	@Override
	public String toString() {
		return String.format("Output: %s\tInput:%s", Arrays.toString(output),
				Arrays.toString(input));
	}
}
