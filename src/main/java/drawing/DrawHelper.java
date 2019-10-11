package drawing;

import assets.AssetLoader;
import simulation.Hotel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Johan Geluk
 * Helper class for drawing things within a component.
 */
public class DrawHelper {

    public static final int SPRITE_SIZE = 32;

    private final AssetLoader assetLoader;

    private Graphics2D graphics;

    // Cache sprites and fonts to prevent them being loaded over and over.
    private Map<String, Image> spriteMap = new HashMap<>();
    private Map<String, Font> fontMap = new HashMap<>();
    private Hotel hotel;
    private float scaleFactor = 2;

    public DrawHelper(AssetLoader assetLoader){
        this.assetLoader = assetLoader;
    }

    public void setGraphics(Graphics2D g){
        this.graphics = g;
    }

    /**
     * Draw some text at the given coordinates, using the font specified.
     * This font needs to be in the assets/fonts directory!
     */
    public void drawString(String text, String fontName, int x, int y) {
        var font = getFont(fontName);
        var size = font.getSize2D();

        var screenX = x * SPRITE_SIZE * scaleFactor;
        var screenY = Math.round(y * SPRITE_SIZE * scaleFactor + size);

        screenX += scaleFactor * 6;
        screenY += scaleFactor * 1;

        graphics.drawString(text, screenX, screenY);
    }

    /**
     * Draw a sprite at the given coordinates.
     */
    public void drawSprite(String spriteName, int x, int y){
        drawSprite(spriteName, Direction.UP, x, y);
    }

    /**
     * Draw a sprite at the given coordinates, pointing it in the given direction.
     */
    public void drawSprite(String spriteName, Direction direction, int x, int y){
        var sprite = getSprite(spriteName);
        drawSprite(sprite, direction, x, y);
    }

    /**
     * Fetch the specified font from cache or disk.
     */
    private Font getFont(String name){
        Font font = fontMap.getOrDefault(name, null);
        if(font == null){
            try {
                font = assetLoader.loadFont(name).deriveFont(16f);
                fontMap.put(name, font);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return font;
    }

    /**
     * Fetch the specified sprite from cache or disk.
     */
    private Image getSprite(String name){
        Image sprite = spriteMap.getOrDefault(name, null);
        if(sprite == null){
            try {
                sprite = assetLoader.loadSprite(name);
                spriteMap.put(name, sprite);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sprite;
    }

    /**
     * Internal draw method, called by all other draw methods.
     */
    private void drawSprite(Image sprite, Direction direction, int x, int y) {
        // Since the coordinate system is flipped, we need to flip our Y-coordinates
        // to ensure the hotel does not get drawn upside down.
        // Then subtract one additional point since origin of each sprite is still in the top left corner,
        // instead of the bottom left. This prevents the top row from being empty.
        y = hotel.getHeight() - y - 1;

        var rotation = direction.ordinal() * Math.PI * 0.5; // 0.5 pi rad = 90°

        // Create a fresh transform to apply to the sprite.
        var transform = new AffineTransform();

        // This is black magic. Scale -> rotate -> translate, right? Nope, not in here!
        // I tried that first but it didn't work, so I messed with it until it did work, and this is the result™
        transform.scale(scaleFactor, scaleFactor);
        transform.translate(x * SPRITE_SIZE + (0.5 * SPRITE_SIZE), y * SPRITE_SIZE + (0.5 * SPRITE_SIZE));
        transform.rotate(rotation);
        transform.translate(-0.5 * SPRITE_SIZE, -0.5 * SPRITE_SIZE);

        // Now just draw the damn thing.
        graphics.drawImage(sprite, transform, null);
    }

    /**
     * Set the hotel to draw. We need this in order to determine the hotel's height,
     * so it gets drawn in the right place.
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Sets the scale at which the hotel should be drawn.
     */
    public void setScaleFactor(float scale) {
        this.scaleFactor = scale;
    }
}
