package pl.wroc.pwr.student.softcomputing.teacher.recognition.border;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.wroc.pwr.student.softcomputing.teacher.api.model.Image;
import pl.wroc.pwr.student.softcomputing.teacher.api.model.Images;

public class BorderImages implements Images<BufferedImage> {

	private final List<Image<BufferedImage>> images = new ArrayList<>();

	@Override
	public void add(Image<BufferedImage> image) {
		images.add(image);
	}

	@Override
	public List<Image<BufferedImage>> list() {
		return Collections.unmodifiableList(images);
	}

}
