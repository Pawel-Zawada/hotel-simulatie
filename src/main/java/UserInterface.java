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
    private static JLabel label = new JLabel("<html><font color='white'>Testing label text</font></html>");
    private final JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 8));

    UserInterface() {
        setup();
        frame.setVisible(true);
    }

    /**
     * Configures the frame and panel to contain base elements and properties, like background color.
     */
    private void setup() {
        contentPanel.add(label);
        contentPanel.setBackground(Color.blue);

        // Set `mainPanel` as the container to all components of the interface.
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to fullscreen mode...
        frame.setUndecorated(true); // ...*without* window bar.
    }
}

