package pl.wroc.pwr.student.softcomputing.ui.main.wrappers;

import javax.swing.JProgressBar;

public class HasProgress {
	
	private JProgressBar progressBar;

	public HasProgress(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	
	public void setValue(int value) {
		progressBar.setValue(value);
	}
	
}
