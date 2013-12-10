package pl.wroc.pwr.student.softcomputing.ui.main.wrappers;

import javax.swing.JRadioButton;

public class HasTeachingType {

	private JRadioButton figureRadioButton;
	private JRadioButton suitRadioButton;

	public HasTeachingType(JRadioButton figureRadioButton,
			JRadioButton suitRadioButton) {
		this.figureRadioButton = figureRadioButton;
		this.suitRadioButton = suitRadioButton;
	}

	public boolean isSuitSelected() {
		return suitRadioButton.isSelected();
	}

	public boolean isFigureSelected() {
		return figureRadioButton.isSelected();
	}
}
