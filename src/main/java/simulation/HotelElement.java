package simulation;

import drawing.Drawable;

/**
 * A basic element (private or public room) of the hotel.
 */
public interface HotelElement extends Drawable {
    int getWidth();
    int getHeight();
    int getX();
    int getY();
}
