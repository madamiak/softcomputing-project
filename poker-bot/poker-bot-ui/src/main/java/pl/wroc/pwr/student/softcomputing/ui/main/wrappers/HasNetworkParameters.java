package pl.wroc.pwr.student.softcomputing.ui.main.wrappers;

import javax.swing.JTextField;

public class HasNetworkParameters {

	private JTextField maxIterationsField;
	private JTextField learningRateField;
	private JTextField errorRateField;
	private JTextField momentumField;

	public HasNetworkParameters(JTextField maxIterationsField,
			JTextField learningRateField, JTextField errorRateField,
			JTextField momentumField) {
		this.maxIterationsField = maxIterationsField;
		this.learningRateField = learningRateField;
		this.errorRateField = errorRateField;
		this.momentumField = momentumField;
	}

	public double[] getAll() {
		double[] params = new double[4];
		try {
			String maxIterationsText = maxIterationsField.getText();
			double maxIterations = Double.parseDouble(maxIterationsText);
			params[0] = maxIterations;
		} catch (NumberFormatException e) {
			params[0] = 0.0;
		}
		try {
			String learningRateText = learningRateField.getText();
			double learningRate = Double.parseDouble(learningRateText);
			params[1] = learningRate;
		} catch (NumberFormatException e) {
			params[1] = 0.0;
		}
		try {
			String errorRateText = errorRateField.getText();
			double errorRate = Double.parseDouble(errorRateText);
			params[2] = errorRate;
		} catch (NumberFormatException e) {
			params[2] = 0.0;
		}
		try {
			String momentumText = momentumField.getText();
			double momentum = Double.parseDouble(momentumText);
			params[3] = momentum;
		} catch (NumberFormatException e) {
			params[3] = 0.0;
		}
		return params;
	}

}
