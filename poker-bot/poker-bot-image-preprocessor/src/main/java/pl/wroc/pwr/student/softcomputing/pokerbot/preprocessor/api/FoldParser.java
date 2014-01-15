package pl.wroc.pwr.student.softcomputing.pokerbot.preprocessor.api;

import java.awt.image.BufferedImage;

public interface FoldParser {
	boolean parseFoldButton(BufferedImage image);
}
