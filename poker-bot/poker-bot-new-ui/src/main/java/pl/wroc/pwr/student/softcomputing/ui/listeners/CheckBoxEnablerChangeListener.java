package pl.wroc.pwr.student.softcomputing.ui.listeners;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by RaV on 04.01.14.
 */
public class CheckBoxEnablerChangeListener implements ChangeListener {
    JTextField field;
    JCheckBox checkBox;
    public CheckBoxEnablerChangeListener(JTextField field, JCheckBox checkBox) {
        this.field = field;
        this.checkBox = checkBox;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        field.setEnabled(checkBox.isSelected());
    }
}
