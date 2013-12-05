package pl.wroc.pwr.student.softcomputing.ui.main;

import java.io.File;

public class MainController {

	private final MainDisplay mainDisplay;

	public MainController(MainDisplay mainDisplay) {
		this.mainDisplay = mainDisplay;
	}
	
	public void onDirectoryChosen(File file) {
		mainDisplay.getInputDirectory().set(file.getAbsolutePath());
		mainDisplay.getFilesList().clearContent();
		
		for (File each : file.listFiles()) {
			if(isFileImageExtension(each)) {
				mainDisplay.getFilesList().addRow(each);
			}
		}
		mainDisplay.refreshList();
	}

	public void onFileChosen(File file) {
		mainDisplay.getOutputFile().set(file.getAbsolutePath());
	}

	private boolean isFileImageExtension(File each) {
		return each.isFile() && each.getName().toLowerCase().substring(each.getName().lastIndexOf(".")).equals(".png");
	}

	public void onImagePreview(File file) {
		mainDisplay.getImage().set(file);
		mainDisplay.refreshCanvas();
	}
}
