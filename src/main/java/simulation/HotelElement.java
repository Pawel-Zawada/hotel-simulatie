package simulation;

import drawing.Drawable;

public interface HotelElement extends Drawable {
    int getWidth();
    int getHeight();
    int getX();
    int getY();
    boolean isWalkable();
}
