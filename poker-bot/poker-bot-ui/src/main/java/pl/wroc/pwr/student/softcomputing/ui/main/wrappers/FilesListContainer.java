package pl.wroc.pwr.student.softcomputing.ui.main.wrappers;

import java.io.File;
import java.util.Vector;

public class FilesListContainer extends Vector<File> implements HasFilesList {

	private static final long serialVersionUID = 1L;

	public void addRow(File file) {
		this.add(file);
	}

	public void clearContent() {
		this.clear();
	}

}
