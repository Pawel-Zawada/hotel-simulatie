package simulation;

import drawing.DrawHelper;
import drawing.Drawable;
import json.JsonReader;

import java.util.ArrayList;

public class Hotel implements Drawable {
    ArrayList<HotelElement> hotelElements = new ArrayList<>();
    private final int width;
    private final int height;

    public Hotel( ArrayList<HotelElement> hotelElements,int width,int height){
        this.hotelElements = hotelElements;
        this.width = width;
        this.height = height;
        System.out.println(hotelElements.get(0));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(DrawHelper drawHelper) {
        drawHelper.drawSprite("player_right", 0,0);
    }
}
