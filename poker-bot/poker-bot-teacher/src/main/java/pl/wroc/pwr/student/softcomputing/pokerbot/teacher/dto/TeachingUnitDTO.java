package pl.wroc.pwr.student.softcomputing.pokerbot.teacher.dto;

public class TeachingUnitDTO {

	private final double[] imageFlattenedArray;
	private final String name;

	public TeachingUnitDTO(double[] image, String name) {
		this.imageFlattenedArray = image;
		this.name = name;
	}

	public double[] image() {
		return imageFlattenedArray;
	}

	public String name() {
		return name;
	}

}
