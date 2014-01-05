package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.ui.components.ImagePanel;
import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;

/**
 * Created by RaV on 04.01.14.
 */
public class ImagePreviewListSelectionListener implements ListSelectionListener {

    JList list;
    ImagePanel imagePanel;
    FileHolder figuresImagesDir;

    public ImagePreviewListSelectionListener(JList list, ImagePanel imagePanel, FileHolder figuresImagesDir) {
        this.list=list;
        this.imagePanel=imagePanel;
        this.figuresImagesDir=figuresImagesDir;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            imagePanel.preview(figuresImagesDir.getFile()+ File.separator+list.getSelectedValue());

            System.out.println("Selected: "+list.getSelectedValue());
        }
    }
}
