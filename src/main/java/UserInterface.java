import javax.swing.*;
import java.awt.*;

/**
 * @author Pawel Zawada
 * Core interface for main interactions between the user and application.
 * Contains functionality that allows the user to manipulate the application through settings.
 * For example: Changing HTE's, switching floors, etc.
 */
class UserInterface extends JFrame {
    private final JFrame frame = new JFrame("Hotel Simulator"); // Main frame to contain the menu panels.

    /**************
     * Containers *
     **************/
    private JMenuBar menuBar = new MenuBar();
    private final MainPanel mainPanel = new MainPanel(); // Container panel to display subcomponents like navigation and content.
    private final NavigationPanel navigationPanel = new NavigationPanel(); // Will act as the container for navigational buttons.
    private final ContentPanel contentPanel = new ContentPanel(); // Contextual content of the currently selected navigation. (For example `settings`)

    /**
     * Build frame & containing panels with respective components.
     */
    private void setup() {
        frame.setJMenuBar(menuBar);

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

    /**
     * Navigation for general options and settings
     */
    private static class MenuBar extends JMenuBar {
        JMenu mainMenu = new SystemMenu();

        MenuBar() {
            add(mainMenu);
            add(Box.createHorizontalGlue()); // Items added after this are glued to the right side of the window.
        }

        private static class SystemMenu extends JMenu {
            JMenuItem exitItem = new JMenuItem("Exit");

            SystemMenu() {
                super("System");

                addSeparator();

                exitItem.addActionListener(e -> System.exit(0)); // Kill the program on button click.
                add(exitItem);
            }
        }
    }

    private static class MainPanel extends JPanel {
        MainPanel() {
            super(new BorderLayout());
        }
    }

    /**
     * Shows options for traversing the hotel through it's floors.
     */
    private static class NavigationPanel extends JPanel {
        Dimension dimension = new Dimension(80, 60);
        FloorsPanel floorsPanel = new FloorsPanel();

        NavigationPanel() {
            super(new BorderLayout());

            setPreferredSize(dimension);
            setBackground(Color.lightGray);

            add(floorsPanel, BorderLayout.LINE_START);
        }

        private static class FloorsPanel extends JPanel {
            FloorsPanel() {
                super(new BorderLayout());

                setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

                add(new JLabel("Floors:"), BorderLayout.PAGE_START);

                // TODO: Split this into inner class
                JPanel panel = new JPanel(new FlowLayout());

                // TODO: Implement button state manager to unselect other buttons on selection
                panel.add(new FloorButton(1));
                panel.add(new FloorButton(2));
                panel.add(new FloorButton(3));
                panel.add(new FloorButton(4));

                add(panel, BorderLayout.LINE_START);
            }

            private static class FloorButton extends JButton {
                boolean selected = false;
                Integer number;

                FloorButton(Integer number) {
                    super(String.format("Floor %s", number));
                    this.number = number;

                    setBackground(Color.white);

                    addActionListener(e -> changeSelectedState(!selected));
                }

                /**
                 * Render button in either `selected` or `unselected` state by changing it's appearance.
                 *
                 * @param selected Boolean value whether or not the button is to shown as selected or unselected.
                 */
                void changeSelectedState(boolean selected) {
                    this.selected = selected;

                    if (selected) {
                        setBackground(Color.blue);
                        setText(String.format("<html><span style='color: white'>Floor %s</span></html>", this.number));
                    } else {
                        setBackground(Color.white);
                        setText(String.format("<html>Floor %s</html>", this.number));
                    }
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

