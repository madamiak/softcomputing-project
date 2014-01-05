package pl.wroc.pwr.student.softcomputing.ui.listeners;

import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;
import pl.wroc.pwr.student.softcomputing.ui.util.TeachingParams;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by RaV on 04.01.14.
 */
public class TeachCardsActionListener implements ActionListener {
    private JCheckBox scaleC;
    private JTextField scaleT;
    private JCheckBox blackAndWhiteC;
    private JCheckBox grayscaleC;
    private JCheckBox maxIterC;
    private JTextField maxIterT;
    private JCheckBox learningRateC;
    private JTextField learningRateT;
    private JCheckBox errorRateC;
    private JTextField errorRateT;
    private JCheckBox momentumC;
    private JTextField momentumT;
    private JTextField filenameT;
    private FileHolder fileDir;
    private List<File> imageFilesList;

    public TeachCardsActionListener(JCheckBox scaleC, JTextField scaleT, JCheckBox blackAndWhiteC, JCheckBox grayscaleC, JCheckBox maxIterC, JTextField maxIterT, JCheckBox learningRateC, JTextField learningRateT, JCheckBox errorRateC, JTextField errorRateT, JCheckBox momentumC, JTextField momentumT, JTextField filenameT, FileHolder fileDir, List<File> imageFilesList) {
        this.scaleC = scaleC;
        this.scaleT = scaleT;
        this.blackAndWhiteC = blackAndWhiteC;
        this.grayscaleC = grayscaleC;
        this.maxIterC = maxIterC;
        this.maxIterT = maxIterT;
        this.learningRateC = learningRateC;
        this.learningRateT = learningRateT;
        this.errorRateC = errorRateC;
        this.errorRateT = errorRateT;
        this.momentumC = momentumC;
        this.momentumT = momentumT;
        this.filenameT = filenameT;
        this.fileDir = fileDir;
        this.imageFilesList = imageFilesList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(imageFilesList.isEmpty()){
            displayMessage("No images selected.");
            return;
        }
        try{
            TeachingParams teachingParams = getTeachingParams();
            System.out.println(teachingParams + "postfix: "+teachingParams.toNamePostfix());
        }catch(NumberFormatException ex){
            displayMessage("One or more fields is filled with wrong value.");
        }catch (UnsupportedOperationException ex){
            displayMessage("Fields cannot be filled with 0 or negative value.");
        }
        if(fileDir.getFile()==null){
            displayMessage("Output directory not selected.");
            return;
        }
        String filename = filenameT.getText();
        if(!isFilenameValid(filename)){
            displayMessage("Wrong filename.");
            return;
        }
        String file = fileDir.getFile()+File.separator+filename+getTeachingParams().toNamePostfix()+".nnet";
        System.out.println(file);
    }

    private boolean isFilenameValid(String filename){
        if(filename.equals(""))
            return false;
        File f = new File(filename);
        try{
            f.getCanonicalPath();
            return true;
        }catch (IOException e){
            return false;
        }
    }

    private TeachingParams getTeachingParams(){
        TeachingParams teachingParams = new TeachingParams();
        if(scaleC.isSelected())
            teachingParams.setScale(Float.parseFloat(scaleT.getText()));
        if(blackAndWhiteC.isSelected())
            teachingParams.setBlackAndWhite(true);
        if(grayscaleC.isSelected())
            teachingParams.setGrayscale(true);
        if(maxIterC.isSelected())
            teachingParams.setMaxIterations(Integer.parseInt(maxIterT.getText()));
        if(learningRateC.isSelected())
            teachingParams.setScale(Float.parseFloat(learningRateT.getText()));
        if(errorRateC.isSelected())
            teachingParams.setScale(Float.parseFloat(errorRateT.getText()));
        if(momentumC.isSelected())
            teachingParams.setScale(Float.parseFloat(momentumT.getText()));

        return  teachingParams;
    }

    private void displayMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }
}
