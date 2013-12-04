package pl.wroc.pwr.student.softcomputing.ui.main;

import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasFilesList;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasImage;

public interface MainDisplay {

	void setFrameVisible(boolean visible);

	void setMainController(MainController mainController);

	HasFilesList getFilesList();
	
	HasImage getImage();

	void refreshList();

	void refreshCanvas();

}