package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation;

import java.util.Arrays;

import org.neuroph.core.learning.DataSet;

import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.api.InputBuilder;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.dto.TeachingUnitDTO;
import pl.wroc.pwr.student.softcomputing.pokerbot.teacher.dto.TeachingUnitsDTO;

public class TeachingInputBuilder implements InputBuilder {

	private boolean figureSelected;
	private boolean suitSelected;

	public TeachingInputBuilder(boolean figureSelected, boolean suitSelected) {
		this.figureSelected = figureSelected;
		this.suitSelected = suitSelected;
	}

	@Override
	public DataSet prepareInput(TeachingUnitsDTO toLearn) {
		DataSet dataSet = createDataSet(toLearn);
		for (TeachingUnitDTO teachingUnit : toLearn.list()) {
			double[] input = teachingUnit.image();
			double[] output = createOutput(teachingUnit.name());
//			System.out.println(Arrays.toString(output));
			dataSet.addRow(input, output);
		}
		return dataSet;
	}

	private DataSet createDataSet(TeachingUnitsDTO toLearn) {
		int inputSize;
		int outputSize;
		inputSize = toLearn.list().get(0).image().length;
		if (figureSelected) {
			outputSize = 4;
		} else {
			outputSize = 2;
		}
		System.out.println("Creating data [input=" + inputSize + ", output="
				+ outputSize + "]");
		DataSet dataSet = new DataSet(inputSize, outputSize);
		return dataSet;
	}

	private double[] createOutput(String name) {
		double[] retVal;
		if (figureSelected) {
			retVal = createFigureOutput(name);
		} else {
			retVal = createSuitOutput(name);
		}
		return retVal;
	}

	private double[] createSuitOutput(String name) {
		System.out.println(name);
		if("h".equals(name)) {
			return new double[] {0.0, 0.0};
		} else if("d".equals(name)) {
			return new double[] {0.0, 1.0};
		} else if("s".equals(name)) {
			return new double[] {1.0, 0.0};
		} else if("c".equals(name)) {
			return new double[] {1.0, 1.0};
		} else {
			return null;
		}
	}

	private double[] createFigureOutput(String name) {
		if ("2".equals(name)) {
			return new double[] { 0.0, 0.0, 0.0, 0.0 };
		} else if ("3".equals(name)) {
			return new double[] { 0.0, 0.0, 0.0, 1.0 };
		} else if ("4".equals(name)) {
			return new double[] { 0.0, 0.0, 1.0, 0.0 };
		} else if ("5".equals(name)) {
			return new double[] { 0.0, 0.0, 1.0, 1.0 };
		} else if ("6".equals(name)) {
			return new double[] { 0.0, 1.0, 0.0, 0.0 };
		} else if ("7".equals(name)) {
			return new double[] { 0.0, 1.0, 0.0, 1.0 };
		} else if ("8".equals(name)) {
			return new double[] { 0.0, 1.0, 1.0, 0.0 };
		} else if ("9".equals(name)) {
			return new double[] { 0.0, 1.0, 1.0, 1.0 };
		} else if ("T".equals(name)) {
			return new double[] { 1.0, 0.0, 0.0, 0.0 };
		} else if ("J".equals(name)) {
			return new double[] { 1.0, 0.0, 0.0, 1.0 };
		} else if ("Q".equals(name)) {
			return new double[] { 1.0, 0.0, 1.0, 0.0 };
		} else if ("K".equals(name)) {
			return new double[] { 1.0, 0.0, 1.0, 1.0 };
		} else if ("A".equals(name)) {
			return new double[] { 1.0, 1.0, 0.0, 0.0 };
		} else {
			return null;
		}
	}

}
