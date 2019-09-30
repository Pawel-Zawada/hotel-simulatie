import javax.swing.*;
import java.awt.*;

/**
 * @author Pawel Zawada
 * Core interface for main interactions between the user and application.
 * Contains functionality that allows the user to manipulate the application through settings.
 * For example: Changing HTE's, switching floors, etc.
 */
class UserInterface extends JFrame {
    private final JFrame frame = new JFrame("Hotel Simulator");

    private final JPanel mainPanel = new JPanel(new BorderLayout()); // Container panel to display subcomponents like navigation and content.

    UserInterface() {
        setup();
        frame.setVisible(true);
    }

    /**
     * Configures the frame and panel to contain base elements and properties, like background color.
     */
    private void setup() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(mainPanel);
    }
}
