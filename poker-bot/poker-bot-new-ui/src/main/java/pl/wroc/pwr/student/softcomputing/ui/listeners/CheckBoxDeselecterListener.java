package pl.wroc.pwr.student.softcomputing.ui.listeners;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by RaV on 04.01.14.
 */
public class CheckBoxDeselecterListener implements ChangeListener {
    JCheckBox selected;
    JCheckBox toDeselect;

    public CheckBoxDeselecterListener(JCheckBox selected, JCheckBox toDeselect) {
        this.selected = selected;
        this.toDeselect = toDeselect;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(selected.isSelected())
            toDeselect.setSelected(false);
    }
}
