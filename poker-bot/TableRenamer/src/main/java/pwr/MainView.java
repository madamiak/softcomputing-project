package pwr;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.StandardCopyOption;

/**
 * Created by RaV on 17.05.14.
 */
public class MainView {

    private JPanel mainPanel;
    private JTextField dirField;
    private JButton dirButton;
    private JTextField nameField;
    private JButton nameButton;
    private JTextField targetDirField;
    private JLabel infoLabel;
    private ImagePanel imagePanel;

    private static int index;
    private static final List<File> tables = new ArrayList<File>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Table renamer");
        frame.setContentPane(new MainView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    public MainView() {
        dirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File dir = new File(dirField.getText());
                tables.addAll(getImages(dir));
                imagePanel.preview(tables.get(0).toString());
                infoLabel.setText("1/"+tables.size());
                for(File f : tables) System.out.println(f);
                index =0;
            }
        });
        nameButton.addActionListener(new RenameActionListener());
        nameField.addActionListener(new RenameActionListener());
    }

    private List<File> getImages(File dir){
        List<File> fileList = new ArrayList<File>();
        if(dir==null)
            JOptionPane.showMessageDialog(null,"First select images directory.");
        else{
            for(final File fileEntry : dir.listFiles()){
                String fileName = fileEntry.toString();
                if(fileEntry.isDirectory())
                    fileList.addAll(getImages(fileEntry));
                if(!fileEntry.isDirectory()&&fileName.endsWith(".png")){
                    fileList.add(fileEntry);
                    System.out.println(fileEntry);
                }
            }
        }
        return fileList;
    }

    private class RenameActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String verify = nameField.getText();
                if(verify.length()!=4){
                    JOptionPane.showMessageDialog(null,"Wrong name");
                    return;
                }
                if(verify.charAt(1) != 'h' && verify.charAt(1) != 's' && verify.charAt(1) != 'c' && verify.charAt(1) != 'd'){
                    JOptionPane.showMessageDialog(null,"Wrong name");
                    return;
                }
                if(verify.charAt(3) != 'h' && verify.charAt(3) != 's' && verify.charAt(3) != 'c' && verify.charAt(3) != 'd'){
                    JOptionPane.showMessageDialog(null,"Wrong name");
                    return;
                }
                if(verify.charAt(0)<'0'||verify.charAt(0)>'9'){
                    if(verify.charAt(0)!='A'&&verify.charAt(0)!='K'&&verify.charAt(0)!='Q'&&verify.charAt(0)!='J'&&verify.charAt(0)!='T'){
                        JOptionPane.showMessageDialog(null,"Wrong name");
                        return;
                    }
                }
                if(verify.charAt(2)<'0'||verify.charAt(2)>'9'){
                    if(verify.charAt(2)!='A'&&verify.charAt(2)!='K'&&verify.charAt(2)!='Q'&&verify.charAt(2)!='J'&&verify.charAt(2)!='T'){
                        JOptionPane.showMessageDialog(null,"Wrong name");
                        return;
                    }
                }
                if(index<tables.size()){
                    String newName = nameField.getText()+(tables.get(index).getName().contains("r") ? "Raise" : "Fold")+"["+(index+1)+"]"+".png";
                    System.out.println("New name: "+newName);
                    try {
                        System.out.println("Source: "+tables.get(index));
                        System.out.println("Dest: "+new File(targetDirField.getText() + File.separator + newName));
                        Files.copy(tables.get(index).toPath(), new File(targetDirField.getText() + File.separator + newName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null,"Something went wrong.\n"+e1);
                        return;
                    }
                    index++;
                    if(index<tables.size())imagePanel.preview(tables.get(index).toString());
                    infoLabel.setText((index+1)+"/"+tables.size());
                    nameField.setText("");
                }
            }
    }
}
