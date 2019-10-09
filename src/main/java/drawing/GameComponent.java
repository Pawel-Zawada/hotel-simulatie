package drawing;

import assets.AssetLoader;
import simulation.Hotel;
import simulation.IObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameComponent extends JComponent implements IObserver {
    private Hotel hotel;
    private Random random = new Random();
    private DrawHelper drawHelper;
    private int baseSeed;

    public GameComponent(AssetLoader assetLoader) {
        this.drawHelper = new DrawHelper(assetLoader);

        setPreferredSize(new Dimension(800, 600));
        setBorder(BorderFactory.createLineBorder(Color.red));
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

//        for(int i = 0; i < 10; i++){
//            for(int j = 0; j < 10; j++){
//                drawHelper.drawSprite("elevator", i, j);
//            }
//        }

        hotel.draw(drawHelper);
    }

    public void setHotel(Hotel hotel) {
        this.baseSeed = new Random().nextInt();
        this.hotel = hotel;
        this.drawHelper.setHotel(hotel);

        var size = getPreferredSize();

        var xScale = (float)size.width / hotel.getWidth() / DrawHelper.SPRITE_SIZE;
        var yScale = (float)size.height / hotel.getHeight() / DrawHelper.SPRITE_SIZE;

        var minScale = Math.min(xScale, yScale);

        drawHelper.setScaleFactor(minScale);

        //var preferredWidth = hotel.getWidth() * DrawHelper.SPRITE_SIZE * ;
        //var preferredHeight = hotel.getHeight() * DrawHelper.SPRITE_SIZE * DrawHelper.SCALE_FACTOR;

        //super.setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        //super.setMinimumSize(new Dimension(preferredWidth, preferredHeight));
        super.repaint();
    }

    @Override
    public void observe() {
        super.repaint();
    }
}