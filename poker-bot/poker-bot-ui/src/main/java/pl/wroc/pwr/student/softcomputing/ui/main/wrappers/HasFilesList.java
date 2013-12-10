package pl.wroc.pwr.student.softcomputing.ui.main.wrappers;

import java.io.File;
import java.util.List;

public interface HasFilesList {

	void addRow(File file);

	void clearContent();

	List<File> getAll();

}
