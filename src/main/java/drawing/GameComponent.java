package drawing;

import assets.AssetLoader;
import simulation.Hotel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameComponent extends JComponent {
    private Hotel hotel;
    private Random random = new Random();
    private DrawHelper drawHelper;
    private int baseSeed;

    public GameComponent(AssetLoader assetLoader) {
        this.drawHelper = new DrawHelper(assetLoader);

        super.setPreferredSize(new Dimension(800, 600));
    }

    @Override
    public void paintComponent(Graphics g) {
        if (hotel == null) {
            return;
        }

        // Always reset the seed every time we draw,
        // so random objects are drawn in the same location.
        drawHelper.setRandomSeed(baseSeed);
        drawHelper.setGraphics((Graphics2D)g);

        hotel.draw(drawHelper);
    }

    public void setHotel(Hotel hotel) {
        this.baseSeed = new Random().nextInt();
        this.hotel = hotel;

        var preferredWidth = hotel.getWidth() * DrawHelper.SPRITE_SIZE * DrawHelper.SCALE_FACTOR;
        var preferredHeight = hotel.getHeight() * DrawHelper.SPRITE_SIZE * DrawHelper.SCALE_FACTOR;

        super.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        super.setMinimumSize(new Dimension(preferredWidth, preferredHeight));
        super.repaint();
    }
}