package pl.wroc.pwr.student.softcomputing.ui.main;

import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasFilesList;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasImage;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasNetworkParameters;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasProgress;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasTeachingParameters;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasTeachingType;
import pl.wroc.pwr.student.softcomputing.ui.main.wrappers.HasTextValue;

public interface MainDisplay {

	void setFrameVisible(boolean visible);

	void setMainController(MainController mainController);

	HasFilesList getFilesList();
	
	HasImage getImage();

	HasTextValue getInputDirectory();
	
	HasTextValue getOutputFile();
	
	HasProgress getProgress();
	
	HasTeachingParameters getTeachingParameters();
	
	HasNetworkParameters getNetworkParameters();
	
	HasTeachingType getTeachingType();
	
	void refreshList();

	void refreshCanvas();

}