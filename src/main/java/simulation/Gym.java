package simulation;

import java.util.ArrayList;

import drawing.DrawHelper;

public class Gym implements HotelElement{
    private int width;
    private int height;
    private int x;
    private int y;

    public Gym(int width, int height, int x, int y){
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

    private ArrayList<Guest> guestsInGym = new ArrayList<>();

    public void enterGym(Guest guest){
        guestsInGym.add(guest);
    }

    public void leaveGym(Guest guest){
        guestsInGym.remove(guest);
    }

    public int totalGuestsInGym(){
        return guestsInGym.size();
    }

    @Override
    public void draw(DrawHelper drawHelper) {
        for(int x = 0; x < this.width; x++){
            // Also support rooms higher than 1.
            for(int y = 0; y < this.height; y++){
                drawHelper.drawSprite("gym", this.x + x, this.y + y);
            }
        }
    }
}
