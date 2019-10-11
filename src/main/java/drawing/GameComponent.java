package drawing;

import assets.AssetLoader;
import simulation.Hotel;
import simulation.HteObserver;

import javax.swing.*;
import java.awt.*;

/**
 * @author Johan Geluk
 */
public class GameComponent extends JComponent implements HteObserver {
    private Hotel hotel;
    private DrawHelper drawHelper;

    public GameComponent(AssetLoader assetLoader) {
        this.drawHelper = new DrawHelper(assetLoader);

        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    public void paintComponent(Graphics g) {
        if (hotel == null) {
            return;
        }

        drawHelper.setGraphics((Graphics2D)g);

        // The hotel draws itself, and recursively requests all its components to draw themselves too.
        hotel.draw(drawHelper);
    }

    /**
     * Sets the hotel that should be drawn by this component.
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
        this.drawHelper.setHotel(hotel);

        // Something about fitting the hotel into its available space.
        var size = getPreferredSize();
        var xScale = (float)size.width / hotel.getWidth() / DrawHelper.SPRITE_SIZE;
        var yScale = (float)size.height / hotel.getHeight() / DrawHelper.SPRITE_SIZE;

        var minScale = Math.min(xScale, yScale);

        drawHelper.setScaleFactor(minScale);

        super.repaint();
    }

    @Override
    /**
     * When called, forces the component to repaint.
     */
    public void observeHTE() {
        super.repaint();
    }
}