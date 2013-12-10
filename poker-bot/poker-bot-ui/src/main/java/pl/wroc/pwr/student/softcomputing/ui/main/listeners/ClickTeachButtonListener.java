package pl.wroc.pwr.student.softcomputing.ui.main.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.wroc.pwr.student.softcomputing.ui.main.MainController;

public class ClickTeachButtonListener implements ActionListener {

	private MainController controller;

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.onTeachExecuted();
	}

	public void setController(MainController controller) {
		this.controller = controller;
	}

}
