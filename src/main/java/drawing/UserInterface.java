package drawing;

import assets.FileAssetLoader;
import simulation.Hotel;
import system.Core;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Pawel Zawada
 * Core interface for main interactions between the user and application.
 * Contains functionality that allows the user to manipulate the application through settings.
 * For example: Changing HotelTimer's, switching floors, etc.
 */
public class UserInterface extends JFrame {
    private final JFrame frame = new JFrame("Hotel Simulator"); // Main frame to contain the menu panels.
    private final JMenuBar menuBar = new MenuBar();

    private final ContainerPanel containerPanel = new ContainerPanel(); // Container panel to display subcomponents like navigation and content.
    private final ContentPanel contentPanel = new ContentPanel(); // Contextual content of the currently selected navigation. (For example `settings`)

    /**
     * Build frame & containing panels with respective components.
     */
    private void setup() {
        frame.setJMenuBar(menuBar);

        // Set `containerPanel` as the container to all components of the interface.
        containerPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setContentPane(containerPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to fullscreen mode...
        frame.setUndecorated(true); // ...*without* window bar.
    }

    public UserInterface() {
        setup();
        frame.setVisible(true);
    }

    /**
     * Contains all elements to be displayed to the end user.
     */
    private static class ContainerPanel extends JPanel {
        ContainerPanel() {
            super(new BorderLayout());
        }
    }

    /**
     * General content like hotel visualisation.
     */
    private static class ContentPanel extends JPanel {
        ContentPanel() {
            super(new FlowLayout(FlowLayout.CENTER, 16, 8));

            JLabel label = new JLabel("<html><font>Testing label text</font></html>");
            add(label);
            setBackground(Color.white);
            GameComponent component = new GameComponent(new FileAssetLoader());
            add(component, BorderLayout.PAGE_END);
            component.setHotel(new Hotel(0));
        }
    }

    /**
     * Navigation for general options and settings
     */
    private class MenuBar extends JMenuBar {
        JMenu mainMenu = new SystemMenu();

        MenuBar() {
            add(mainMenu);
            add(Box.createHorizontalGlue()); // Items added after this are glued to the right side of the window.
        }

        /**
         * System related options, including the exit button.
         */
        private class SystemMenu extends JMenu {
            JMenuItem exitItem = new JMenuItem("Exit");
            JMenuItem settingsItem = new JMenuItem("Settings");

            SettingsDialog settingsDialog = new SettingsDialog();

            SystemMenu() {
                super("System");

                settingsItem.addActionListener(e -> settingsDialog.setVisible(true)); // Open settings dialog.
                add(settingsItem);

                addSeparator();

                exitItem.addActionListener(e -> System.exit(0)); // Kill the program on button click.
                add(exitItem);
            }

            /**
             * General settings like changing HTE value.
             */
            private class SettingsDialog extends JDialog {
                HTESlider HTESlider = new HTESlider();
                JLabel label = new JLabel("Milliseconds interval per HTE tick");

                SettingsDialog() {
                    super(UserInterface.this.frame, "Settings", true);
                    setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

                    setSize(new Dimension(800, 900));
                    setLocationRelativeTo(null);

                    label.setAlignmentX(LEFT_ALIGNMENT);
                    label.setBorder(new EmptyBorder(5, 5, 0, 0));

                    // HTE setting label & slider
                    add(label);
                    add(HTESlider);
                }

                /**
                 * Slider that enables the user to change the amount of HTE ticks every X amount of seconds.
                 */
                private class HTESlider extends JSlider {
                    static final int HTE_MIN = 500;
                    static final int HTE_MAX = 5000;
                    static final int HTE_MINOR_SPACING = 500;
                    static final int HTE_MAJOR_SPACING = 500;

                    HTESlider() {
                        super(JSlider.HORIZONTAL, HTE_MIN, HTE_MAX, Core.hotelTimer.getHTE());

                        // Display labels at major tick marks.
                        setMinorTickSpacing(HTE_MINOR_SPACING);
                        setMajorTickSpacing(HTE_MAJOR_SPACING);

                        setPaintTicks(true);
                        setPaintLabels(true);
                        setSnapToTicks(true);
                        setAlignmentX(LEFT_ALIGNMENT);

                        // Change HTE value on every slider value change. Should not mess up simulation as the dialog freezes the frame.
                        addChangeListener(e -> Core.hotelTimer.setHTE(getValue()));
                    }
                }
            }
        }
    }
}

