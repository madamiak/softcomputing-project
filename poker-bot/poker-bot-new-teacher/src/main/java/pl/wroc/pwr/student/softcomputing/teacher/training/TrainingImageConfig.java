package pl.wroc.pwr.student.softcomputing.teacher.training;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.ImageConfig;

public class TrainingImageConfig implements ImageConfig {

	private final double scale;
	private final boolean blackAndWhite;
	private final boolean grayscale;

	public TrainingImageConfig(double scale, boolean blackAndWhite,
			boolean grayscale) {
		this.scale = scale;
		this.blackAndWhite = blackAndWhite;
		this.grayscale = grayscale;
	}

	@Override
	public double getScale() {
		return scale;
	}

	@Override
	public boolean isBlackAndWhite() {
		return blackAndWhite;
	}

	@Override
	public boolean isGrayscale() {
		return grayscale;
	}

}
