package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.implementation;

import java.awt.image.BufferedImage;
import java.io.File;

public class TrainingUnit {
	
	private File value;
	private BufferedImage image;
	
	public TrainingUnit(File value, BufferedImage image) {
		super();
		this.value = value;
		this.image = image;
	}
	public File getValue() {
		return value;
	}
	public void setValue(File value) {
		this.value = value;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}

