package simulation;

import drawing.DrawHelper;

public class CheckInDesk implements HotelElement{
    private int width;
    private int height;
    private int x;
    private int y;

    public CheckInDesk(int width, int height,int x,int y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }


    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWalkable() {
        return false;
    }

    @Override
    public void draw(DrawHelper drawHelper) {
        drawHelper.drawSprite("player_right", x, y);
    }
}
