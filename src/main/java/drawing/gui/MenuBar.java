package drawing.gui;

import drawing.UserInterface;
import drawing.gui.dialog.SettingsDialog;
import drawing.gui.dialog.StatisticsDialog;
import simulation.Hotel;

import javax.swing.*;

/**
 * Navigation for general options and settings
 */
public class MenuBar extends JMenuBar {
    public MenuBar(UserInterface userInterface, Hotel hotel) {
        JMenu mainMenu = new SystemMenu(userInterface, hotel);
        add(mainMenu);
        add(Box.createHorizontalGlue()); // Items added after this are glued to the right side of the window.
    }

    /**
     * System related options, including the exit button.
     */
    private static class SystemMenu extends JMenu {
        JMenuItem statisticsItem = new JMenuItem("Statistics");
        JMenuItem settingsItem = new JMenuItem("Settings");
        JMenuItem exitItem = new JMenuItem("Exit");

        StatisticsDialog statisticsDialog;
        SettingsDialog settingsDialog;

        SystemMenu(UserInterface userInterface, Hotel hotel) {
            super("System");

            statisticsDialog = new StatisticsDialog(userInterface, hotel);
            settingsDialog = new SettingsDialog(userInterface, hotel);

            statisticsItem.addActionListener(e -> statisticsDialog.setVisible(true)); // Open statistics dialog.
            add(statisticsItem);

            settingsItem.addActionListener(e -> settingsDialog.setVisible(true)); // Open settings dialog.
            add(settingsItem);

            addSeparator();

            exitItem.addActionListener(e -> System.exit(0)); // Kill the program on button click.
            add(exitItem);
        }
    }
}
