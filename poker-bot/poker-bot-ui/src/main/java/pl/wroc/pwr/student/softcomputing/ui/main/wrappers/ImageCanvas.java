package pl.wroc.pwr.student.softcomputing.ui.main.wrappers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageCanvas extends JPanel implements HasImage {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public void set(File file) {
		try {
			image = ImageIO.read(file);
		} catch (IOException ex) {
			System.err.println(ex.getStackTrace());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image.getScaledInstance(240, 150, Image.SCALE_DEFAULT), 0, 0, null);
		}
	}
}
