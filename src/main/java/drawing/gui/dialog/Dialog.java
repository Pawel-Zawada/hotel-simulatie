package drawing.gui.dialog;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that contains default logic for setting up the layout, size and location of the dialog.
 * Dialog extend this dialog to ensure DRY code and not repeat the setup logic.
 */
abstract class Dialog extends JDialog {
    Dialog(String title, Frame frame) {
        super(frame, title, true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        setSize(new Dimension(800, 900));
        setLocationRelativeTo(null);
    }
}
