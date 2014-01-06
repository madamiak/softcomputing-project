package pl.wroc.pwr.student.softcomputing.teacher.training;

import java.awt.image.BufferedImage;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;

public abstract class TrainingImage implements Image<BufferedImage> {

	private final BufferedImage image;
	private final String name;

	public TrainingImage(BufferedImage image, String name) {
		this.image = image;
		this.name = name;
	}

	@Override
	public BufferedImage getData() {
		return image;
	}

	@Override
	public String getName() {
		return name;
	}

}
