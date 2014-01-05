package pl.wroc.pwr.student.softcomputing.ui.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by RaV on 04.01.14.
 */
public class ImagePanel extends JPanel {

    private BufferedImage image;

    public void preview(String imageDir){
        try {
            image = ImageIO.read(new File(imageDir));
        } catch (IOException ex) {
            System.out.println("Image read exception.");
        }
        float hRatio = (float)getHeight()/image.getHeight();
        float wRatio = (float)getWidth()/image.getWidth();
        float ratio = hRatio < wRatio ? hRatio : wRatio;
        if(ratio<1)
            scaleImage(ratio);
        repaint();
    }

    private void scaleImage(float ratio){
        int w = (int)(image.getWidth()*ratio);
        int h = (int)(image.getHeight()*ratio);
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, w, h, null);
        g2.dispose();
        image = resizedImg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image!=null){
            int x =(getWidth()-image.getWidth())/2;
            int y =(getHeight()-image.getHeight())/2;
            g.drawImage(image, x, y, null); // see javadoc for more info on the parameters
        }
    }
}
