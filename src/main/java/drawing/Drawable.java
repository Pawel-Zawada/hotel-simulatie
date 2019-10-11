package drawing;

/**
 * @author Johan Geluk
 * Can be applied to any objects that want to draw themselves.
 */
public interface Drawable {
    /**
     * Called when an object should draw itself.
     * @param drawHelper A class containing some helper functions for drawing sprites on the screen.
     */
    void draw(DrawHelper drawHelper);
}