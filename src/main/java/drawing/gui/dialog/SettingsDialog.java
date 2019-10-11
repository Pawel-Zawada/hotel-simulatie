package drawing.gui.dialog;

import drawing.UserInterface;
import drawing.gui.HTESlider;
import simulation.Hotel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * General settings like changing HTE value.
 */
public class SettingsDialog extends Dialog {
    public SettingsDialog(UserInterface userInterface, Hotel hotel) {
        super("Settings", userInterface);

        HTESlider hteSlider = new HTESlider(hotel);

        JLabel label = new JLabel("Milliseconds interval per HTE tick");
        label.setAlignmentX(LEFT_ALIGNMENT);
        hteSlider.setAlignmentX(LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(5, 5, 0, 0));

        // Add the HTE setting label & slider to the dialog's content.
        add(label);
        add(hteSlider);
    }
}
