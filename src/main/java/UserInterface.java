import javax.swing.*;
import java.awt.*;

/**
 * @author Pawel Zawada
 * Core interface for main interactions between the user and application.
 * Contains functionality that allows the user to manipulate the application through settings.
 * For example: Changing HotelTimer's, switching floors, etc.
 */
class UserInterface extends JFrame {
    private final JFrame frame = new JFrame("Hotel Simulator"); // Main frame to contain the menu panels.

    /**************
     * Containers *
     **************/
    private final MainPanel mainPanel = new MainPanel(); // Container panel to display subcomponents like navigation and content.
    private final NavigationPanel navigationPanel = new NavigationPanel(); // Will act as the container for navigational buttons.
    private final ContentPanel contentPanel = new ContentPanel(); // Contextual content of the currently selected navigation. (For example `settings`)

    /**
     * Build frame & containing panels with respective components.
     */
    private void setup() {
        // Set `mainPanel` as the container to all components of the interface.
        mainPanel.add(navigationPanel, BorderLayout.PAGE_START);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to fullscreen mode...
        frame.setUndecorated(true); // ...*without* window bar.
    }

    UserInterface() {
        setup();
        frame.setVisible(true);
    }

    /**********************************************
     * Custom containers w/ respective components *
     **********************************************/

    private static class MainPanel extends JPanel {
        MainPanel() {
            super(new BorderLayout());
        }
    }

    private static class NavigationPanel extends JPanel {
        Dimension dimension = new Dimension(80, 60);
        FloorsPanel floorsPanel = new FloorsPanel();
        ExitPanel exitPanel = new ExitPanel();

        NavigationPanel() {
            super(new BorderLayout());

            setPreferredSize(dimension);
            setBackground(Color.lightGray); // TODO: Remove this panel marker.

            add(floorsPanel, BorderLayout.LINE_START);
            add(exitPanel, BorderLayout.LINE_END);
        }

        private static class FloorsPanel extends JPanel {
            FloorsPanel() {
                super(new BorderLayout());

                setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

                add(new JLabel("Floors:"), BorderLayout.PAGE_START);

                // TODO: Split this into inner class
                JPanel panel = new JPanel(new FlowLayout());
                panel.add(new JButton("Floor 1"));
                panel.add(new JButton("Floor 2"));
                panel.add(new JButton("Floor 3"));
                panel.add(new JButton("Floor 4"));

                add(panel, BorderLayout.LINE_START);
            }
        }

        private class ExitPanel extends JPanel {
            ExitButton exitButton = new ExitButton("Exit", dimension);

            ExitPanel() {
                setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                add(exitButton);
            }

            private class ExitButton extends JButton {

                /**
                 * @param text      Text to be displayed in the button
                 * @param dimension Button's width & height size
                 * @see JButton
                 */
                ExitButton(String text, Dimension dimension) {
                    super(String.format("<html><span style='color: white'>%s</span></html>", text)); // Set button's text with JButton's constructor.

                    setPreferredSize(new Dimension(
                            dimension.width,
                            dimension.height - 10
                    )); // Same size as containing panel, minus 10 pixels in height margin.

                    setBackground(Color.red); // Make it white to remove the ugly metallic gradient.
                    addActionListener(e -> System.exit(0)); // Kill the program on button click.
                }
            }
        }
    }

    private static class ContentPanel extends JPanel {
        ContentPanel() {
            super(new FlowLayout(FlowLayout.CENTER, 16, 8));

            JLabel label = new JLabel("<html><font>Testing label text</font></html>");
            add(label);
            setBackground(Color.white); // TODO: Remove this panel marker.
        }
    }
}

