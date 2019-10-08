package drawing;

import assets.AssetLoader;
import simulation.Direction;
import simulation.Hotel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DrawHelper {

    public static final int SPRITE_SIZE = 32;
    public static final int SCALE_FACTOR = 1;

    private final AssetLoader assetLoader;
    private final Random random = new Random();

    private Graphics2D graphics;
    private Map<String, Image> spriteMap = new HashMap<>();
    private Map<String, Image[]> spriteSetMap = new HashMap<>();
    private Map<String, Font> fontMap = new HashMap<>();
    private int originX;
    private int originY;
    private Hotel hotel;

    public DrawHelper(AssetLoader assetLoader){
        this.assetLoader = assetLoader;
    }

    public void setGraphics(Graphics2D g){
        this.graphics = g;
    }

    public void setRandomSeed(long seed) {
        random.setSeed(seed);
    }

    public void setOrigin(int x, int y){
        originX = x;
        originY = y;
    }

    public int getOriginX(){
        return originX;
    }

    public int getOriginY(){
        return originY;
    }

    public void drawString(String text, String fontName, int x, int y) {
        var font = getFont(fontName);
        var size = font.getSize2D();

        var screenX = x * SPRITE_SIZE * SCALE_FACTOR;
        var screenY = Math.round(y * SPRITE_SIZE * SCALE_FACTOR + size);

        screenX += SCALE_FACTOR * 6;
        screenY += SCALE_FACTOR * 1;

        graphics.drawString(text, screenX, screenY);
    }

    public void drawSprite(String spriteName, int x, int y){
        drawSprite(spriteName, Direction.UP, x, y);
    }

    public void drawSprite(String spriteName, Direction direction, int x, int y){
        var sprite = getSprite(spriteName);
        drawSprite(sprite, direction, x, y);
    }

    public void drawRandomizedSprite(String spriteName, int x, int y) {
        Image[] spriteSet;
        spriteSet = getSpriteSet(spriteName);

        if (random.nextDouble() < 0.85) {
            drawSprite(spriteSet[0], Direction.UP, x, y);
            return;
        }
        for (int i = 1; i < spriteSet.length; i++) {
            if (random.nextDouble() < 1f / (spriteSet.length - i)) {
                drawSprite(spriteSet[i], Direction.UP, x, y);
                return;
            }
        }
    }

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

    private Image[] getSpriteSet(String name){
        Image[] sprites = spriteSetMap.getOrDefault(name, null);
        if(sprites == null){
            try {
                sprites = assetLoader.loadSpriteSet(name);
                spriteSetMap.put(name, sprites);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sprites;
    }

    private void drawSprite(Image sprite, Direction direction, int x, int y) {
        y = hotel.getHeight() - y - 1;

        var rotation = direction.ordinal() * Math.PI * 0.5; // 1/2pi rad = 90°

        // Create a fresh transform to apply to the sprite.
        var transform = new AffineTransform();

        transform.scale(SCALE_FACTOR, SCALE_FACTOR);
        transform.translate(x * SPRITE_SIZE + (0.5 * SPRITE_SIZE), y * SPRITE_SIZE + (0.5 * SPRITE_SIZE));
        transform.rotate(rotation);
        transform.translate(-0.5 * SPRITE_SIZE, -0.5 * SPRITE_SIZE);

        graphics.drawImage(sprite, transform, null);
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
