package pl.wroc.pwr.student.softcomputing.ui.main.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import pl.wroc.pwr.student.softcomputing.ui.main.MainController;

public class ChooseDirectoryListener implements ActionListener {

	private MainController controller;

	public void actionPerformed(ActionEvent e) {
		if (controller == null) {
			return;
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setCurrentDirectory(new File(".."));
		fileChooser.showOpenDialog(null);

		if (fileChooser.getSelectedFile() != null) {
			controller.onDirectoryChosen(fileChooser.getSelectedFile());
		}
	}

	public void setController(MainController controller) {
		this.controller = controller;
	}

}
