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
    private static JLabel label = new JLabel("<html><font color='white'>Testing label text</font></html>"); // HTML FTW
    private final JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 8)); // Will act as the container for navigational buttons.
    private final JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 8)); // Contextual content of the currently selected navigation. (For example `settings`)
    private final JButton exitButton = new ExitButton("Exit");

    /**
     * Build frame & containing panels with respective components.
     */
    private void setup() {
        configurePanels();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to fullscreen mode...
        frame.setUndecorated(true); // ...*without* window bar.
    }

    /**
     * Configure panel setup within the frame, including the panel's components like buttons.
     */
    private void configurePanels() {
        navigationPanel.setPreferredSize(new Dimension(0, 45)); // Low height so it acts as a sort of `toolbar` at the top.
        navigationPanel.add(exitButton); // TODO: Move this to the right side of the panel.
        navigationPanel.setBackground(Color.red);

        contentPanel.add(label);
        contentPanel.setBackground(Color.blue);

        // Set `mainPanel` as the container to all components of the interface.
        mainPanel.add(navigationPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
    }

    UserInterface() {
        setup();
        frame.setVisible(true);
    }

    static class ExitButton extends JButton {
        ExitButton(String text) {
            super(text); // Set title using JButton's constructor.

            setBackground(Color.white); // Make it white to remove the ugly metallic gradient.
            addActionListener(e -> System.exit(0)); // Kill the program on button click.
        }
    }
}

