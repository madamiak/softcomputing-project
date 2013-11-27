package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class TrainingUnitReader {

	private TrainingUnitReader() {
	}

	public static List<TrainingUnit> readTrainingUnitsFromDir(String dir) {

		File file;
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		List<TrainingUnit> list = new ArrayList<TrainingUnit>();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				file = listOfFiles[i];
				if (file.getName().endsWith(".png") || file.getName().endsWith(".PNG")) {
					try {
						File input = new File(dir + "\\" + file.getName());
						list.add(new TrainingUnit(file, ImageIO.read(input)));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return list;
	}

}
