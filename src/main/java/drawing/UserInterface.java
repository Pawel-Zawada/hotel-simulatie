package drawing;

import assets.FileAssetLoader;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import simulation.Hotel;
import simulation.IObserver;
import system.DataCollector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Pawel Zawada
 * Core interface for main interactions between the user and application.
 * Contains functionality that allows the user to manipulate the application through settings.
 * For example: Changing HotelTimer's, switching floors, etc.
 */
public class UserInterface extends JFrame {
    private final JFrame frame = new JFrame("Hotel Simulator"); // Main frame to contain the menu panels.
    private final JMenuBar menuBar;

    private ContainerPanel containerPanel; // Container panel to display subcomponents like navigation and content.
    private ContentPanel contentPanel; // Contextual content of the currently selected navigation. (For example `settings`)
    private Hotel hotel;
    private DataCollector dataCollector;

    /**
     * Build frame & containing panels with respective components.
     */
    private void setup() {
        contentPanel = new ContentPanel(hotel);
        frame.setJMenuBar(menuBar);

        // Set `containerPanel` as the container to all components of the interface.
        containerPanel.add(contentPanel, BorderLayout.CENTER);

        frame.setContentPane(containerPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 1000);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to fullscreen mode...
        frame.setUndecorated(true); // ...*without* window bar.
    }

    public UserInterface(Hotel hotel) {
        this.hotel = hotel;
        this.dataCollector = new DataCollector(hotel);
        this.menuBar = new MenuBar();
        this.containerPanel = new ContainerPanel();
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
        ContentPanel(Hotel hotel) {
            super(new FlowLayout());

            setBackground(Color.white);
            GameComponent component = new GameComponent(new FileAssetLoader());
            add(component, BorderLayout.PAGE_END);
            component.setHotel(hotel);

            hotel.getHotelTimer().addAfterEventObserver(component);

            setBorder(BorderFactory.createLineBorder(Color.blue));
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
            JMenuItem statisticsItem = new JMenuItem("Statistics");
            JMenuItem settingsItem = new JMenuItem("Settings");
            JMenuItem exitItem = new JMenuItem("Exit");

            StatisticsDialog statisticsDialog = new StatisticsDialog();
            SettingsDialog settingsDialog = new SettingsDialog();

            SystemMenu() {
                super("System");

                statisticsItem.addActionListener(e -> statisticsDialog.setVisible(true)); // Open statistics dialog.
                add(statisticsItem);

                settingsItem.addActionListener(e -> settingsDialog.setVisible(true)); // Open settings dialog.
                add(settingsItem);

                addSeparator();

                exitItem.addActionListener(e -> System.exit(0)); // Kill the program on button click.
                add(exitItem);
            }

            /**
             * Abstract class that contains default logic for setting up the layout, size and location of the dialog.
             * Dialog extend this dialog to ensure DRY code and not repeat the setup logic.
             */
            private abstract class Dialog extends JDialog {
                Dialog(String title) {
                    super(UserInterface.this.frame, title, true);
                    setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

                    setSize(new Dimension(800, 900));
                    setLocationRelativeTo(null);
                }
            }

            /**
             * Slider that enables the user to change the amount of HTE ticks every X amount of seconds.
             */
            private class HTESlider extends JSlider {
                // TODO: The higher the slider value, the faster the simulation goes.
                static final int HTE_MIN = 0;
                static final int HTE_MAX = 1000;
                static final int HTE_MINOR_SPACING = 100;
                static final int HTE_MAJOR_SPACING = 500;

                HTESlider() {
                    super(JSlider.HORIZONTAL, HTE_MIN, HTE_MAX, hotel.getHotelTimer().getHTE());

                    // Display labels at major tick marks
                    setMinorTickSpacing(HTE_MINOR_SPACING);
                    setMajorTickSpacing(HTE_MAJOR_SPACING);

                    // Display labels at major and minor spacing values
                    setPaintTicks(true);
                    setPaintLabels(true);
                    setSnapToTicks(true); // Snap the slider to only major and minor marks, no free floating values.
                    setAlignmentX(LEFT);

                    // Change HTE value on every slider value change. Should not mess up simulation as the dialog freezes the frame.
                    addChangeListener(e -> {
                        JSlider source = (JSlider) e.getSource();
                        if (!source.getValueIsAdjusting()) {
                            hotel.getHotelTimer().setHTE(source.getValue());
                        }
                    });
                }
            }

            // TODO: These `StatisticsDialog` & `SettingsDialog` need code splitting. Throw them into their own package or something.

            /**
             * Display statistical data on various hotel elements, like guest count, task queue
             */
            private class StatisticsDialog extends Dialog {
                // Slider making possible the changing the HTE value of the simulation.
                HTESlider HTESlider = new HTESlider();

                // Chart data storage for the x & y coordinates
                ArrayList<Double> xData = new ArrayList<>();
                ArrayList<Double> yData = new ArrayList<>();

                private XYChart chart;
                private XChartPanel xChartPanel; // Used to render the chart into

                StatisticsDialog() {
                    super("Statistics");

                    HTESlider.setAlignmentX(CENTER_ALIGNMENT);
                    add(HTESlider);

                    // Assign initial values
                    updateData();

                    // Create chart with initial data
                    chart = QuickChart.getChart("Totaal taken per tick", "Tick", "Tasks", "tasks", xData, yData);

                    // Display chart panel in dialog
                    xChartPanel = new XChartPanel<>(chart);
                    add(xChartPanel); // Add the panel into the dialog

                    // Update statistics every tick
                    hotel.getHotelTimer().addObserver(new StatisticsObserver());
                }

                /**
                 * Update the data arrays with the latest tick number and number of tasks.
                 */
                private void updateData() {
                    xData.add((double) hotel.getHotelTimer().getHTEIteration());
                    yData.add((double) dataCollector.getNumberOfTasks());
                }

                /**
                 * Update the chart data on every tick event.
                 */
                private class StatisticsObserver implements IObserver {
                    @Override
                    public void observe() {
                        updateData(); // Update the parent class's x & y data variables with the newest values

                        // Update the chart's data with the newly set x & y data from `updateData()`.
                        chart.updateXYSeries("tasks", xData, yData, null);

                        // Repaint the chart's panel
                        xChartPanel.revalidate();
                        xChartPanel.repaint();

                        System.out.println(String.format("Number of tasks %s", dataCollector.getNumberOfTasks()));
                    }
                }
            }

            /**
             * General settings like changing HTE value.
             */
            private class SettingsDialog extends Dialog {
                HTESlider HTESlider = new HTESlider();
                JLabel label = new JLabel("Milliseconds interval per HTE tick");

                SettingsDialog() {
                    super("Settings");

                    label.setAlignmentX(LEFT_ALIGNMENT);
                    HTESlider.setAlignmentX(LEFT_ALIGNMENT);
                    label.setBorder(new EmptyBorder(5, 5, 0, 0));

                    // Add the HTE setting label & slider to the dialog's content.
                    add(label);
                    add(HTESlider);
                }
            }
        }
    }
}

