package drawing;

import assets.FileAssetLoader;
import drawing.gui.MenuBar;
import simulation.Hotel;

import javax.swing.*;
import java.awt.*;

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

    public UserInterface(Hotel hotel) {
        this.hotel = hotel;
        this.menuBar = new MenuBar(this, hotel);
        this.containerPanel = new ContainerPanel();
        setup();
        frame.setVisible(true);
    }

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
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to fullscreen mode...
        //frame.setUndecorated(true); // ...*without* window bar.
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
}

