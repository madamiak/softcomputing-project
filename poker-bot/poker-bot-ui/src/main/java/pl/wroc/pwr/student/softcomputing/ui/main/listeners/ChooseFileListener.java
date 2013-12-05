package pl.wroc.pwr.student.softcomputing.ui.main.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import pl.wroc.pwr.student.softcomputing.ui.main.MainController;

public class ChooseFileListener implements ActionListener {

	private MainController controller;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(controller == null) {
			return;
		}
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new ExtensionFileFilter("Neural network format (*.nnet)", "nnet"));
		fileChooser.setApproveButtonText("Choose");
		fileChooser.setDialogTitle("Choose file to save");
		fileChooser.setCurrentDirectory(new File(".."));
		fileChooser.showOpenDialog(null);
		
		if (fileChooser.getSelectedFile() != null) {
			controller.onFileChosen(fileChooser.getSelectedFile());
		}
	}

	public void setController(MainController mainController) {
		this.controller = mainController;
	}

	class ExtensionFileFilter extends FileFilter {
		private String description;
		private String extensions[];

		public ExtensionFileFilter(String description, String... extensions) {
			this.description = description;
			this.extensions = toLowerCase(extensions);
		}

		private String[] toLowerCase(String[] array) {
			for (int i = 0, n = array.length; i < n; i++) {
			      array[i] = array[i].toLowerCase();
			    }
			return array;
		}

		@Override
		public boolean accept(File file) {
			if (file.isDirectory()) {
			      return true;
			    } else {
			      String path = file.getAbsolutePath().toLowerCase();
			      for (int i = 0, n = extensions.length; i < n; i++) {
			        String extension = extensions[i];
			        if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
			          return true;
			        }
			      }
			    }
			    return false;
		}

		@Override
		public String getDescription() {
			return description;
		}

	}
}
