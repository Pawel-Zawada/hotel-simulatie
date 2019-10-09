package simulation;

import drawing.DrawHelper;
import drawing.Drawable;

public class Hotel implements Drawable {
    private Floor[] floors;

    public Hotel(int numberOfFloors){
        this.floors = new Floor[numberOfFloors];
    }

    public Floor[] getFloors(){
        return floors;
    }

    public int getWidth() {
        return 10;
    }

    public int getHeight() {
        return 10;
    }


    @Override
    public void draw(DrawHelper drawHelper) {
        drawHelper.drawSprite("player_right", 0,0);
    }
}
