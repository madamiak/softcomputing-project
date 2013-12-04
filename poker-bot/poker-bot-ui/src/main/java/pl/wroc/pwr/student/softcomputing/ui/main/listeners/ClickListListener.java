package pl.wroc.pwr.student.softcomputing.ui.main.listeners;

import java.io.File;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.wroc.pwr.student.softcomputing.ui.main.MainController;

public class ClickListListener implements ListSelectionListener {

	private MainController controller;

	@SuppressWarnings("unchecked")
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			controller.onImagePreview(((JList<File>)e.getSource()).getSelectedValue());
		}
	}

	public void setController(MainController controller) {
		this.controller = controller;
	}

}
