package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import json.JsonReader;

import java.util.ArrayList;

public class Hotel implements Drawable {
    ArrayList<HotelElement> hotelElements;
    private final int width;
    private final int height;

    public Hotel( ArrayList<HotelElement> hotelElements,int width,int height){
        this.hotelElements = hotelElements;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(DrawHelper drawHelper) {
        drawHelper.drawSprite("player_right", 0,0);
        //loop over hotel elements and draw elements
    }
}
