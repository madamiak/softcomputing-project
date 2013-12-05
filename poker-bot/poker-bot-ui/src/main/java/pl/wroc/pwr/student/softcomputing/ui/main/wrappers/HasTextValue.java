package pl.wroc.pwr.student.softcomputing.ui.main.wrappers;

import javax.swing.JTextField;

public class HasTextValue {

	private final JTextField inputDirectoryPathField;

	public HasTextValue(JTextField inputDirectoryPathField) {
		this.inputDirectoryPathField = inputDirectoryPathField;
	}

	public void set(String text) {
		inputDirectoryPathField.setText(text);
	}

}
