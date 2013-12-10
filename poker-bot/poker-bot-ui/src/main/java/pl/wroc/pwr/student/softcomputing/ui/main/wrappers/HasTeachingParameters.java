package pl.wroc.pwr.student.softcomputing.ui.main.wrappers;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class HasTeachingParameters {

	private JCheckBox scaleCheckBox;
	private JTextField scaleTextField;
	private JCheckBox blackAndWhiteCheckBox;
	private JCheckBox grayedScaleCheckBox;

	public HasTeachingParameters(JCheckBox scaleCheckBox,
			JTextField scaleTextField, JCheckBox blackAndWhiteCheckBox,
			JCheckBox grayedScaleCheckBox) {
		this.scaleCheckBox = scaleCheckBox;
		this.scaleTextField = scaleTextField;
		this.blackAndWhiteCheckBox = blackAndWhiteCheckBox;
		this.grayedScaleCheckBox = grayedScaleCheckBox;
	}

	public boolean isScaled() {
		return scaleCheckBox.isSelected();
	}

	public double getScale() {
		double scale;
		try {
			scale = Double.parseDouble(scaleTextField.getText());
		} catch (NumberFormatException e) {
			scale = 1.0;
		}
		return scale;
	}

	public boolean isBlackAndWhite() {
		return blackAndWhiteCheckBox.isSelected();
	}

	public boolean isGrayedScale() {
		return grayedScaleCheckBox.isSelected();
	}
}
