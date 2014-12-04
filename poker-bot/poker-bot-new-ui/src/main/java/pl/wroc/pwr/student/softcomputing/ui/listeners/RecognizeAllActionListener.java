package pl.wroc.pwr.student.softcomputing.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import pl.wroc.pwr.student.softcomputing.ui.util.FileHolder;
import pl.wroc.pwr.student.softcomputing.ui.util.RecognizingDelegate;

public class RecognizeAllActionListener implements ActionListener {
	private List<File> imageFilesList;
    private JTextArea output;
    private JList imageJList;
    private final FileHolder figuresDatasource;
    private final FileHolder suitsDatasource;
    private final FileHolder dealerDatasource;

    public RecognizeAllActionListener(List<File> imageFilesList, JTextArea output, JList imageJList, FileHolder figuresDatasource, FileHolder suitsDatasource, FileHolder dealerDatasource) {
        this.imageFilesList = imageFilesList;
        this.output = output;
        this.imageJList = imageJList;
        this.figuresDatasource = figuresDatasource;
        this.suitsDatasource = suitsDatasource;
        this.dealerDatasource = dealerDatasource;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(imageFilesList.isEmpty()){
            displayMessage("No images selected.");
            return;
        }
        String out=RecognizingDelegate.recognizeAll(getAllFiles(), figuresDatasource, suitsDatasource, dealerDatasource);
        output.setText(out);
	}

    private void displayMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }

    public List<File> getAllFiles() {
        return imageFilesList;
    }

}
