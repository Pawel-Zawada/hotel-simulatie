package simulation;

import java.util.ArrayList;

import drawing.DrawHelper;

public class Elevator implements HotelElement {
    private int width;
    private int height;
    private int x;
    private int y;

    public Elevator(int width, int height, int x, int y){
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

    public static ArrayList<Guest> guestsInElevator = new ArrayList<>();

    public void guestStepInElevator(Guest guest){
        guestsInElevator.add(guest);
    }

    public void guestStepOutOfElevator(Guest guest){
        guestsInElevator.remove(guest);
    }

    public int numberOfGuestsInElevator(){
        return guestsInElevator.size();
    }

    @Override
    public void draw(DrawHelper drawHelper) {
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                drawHelper.drawSprite("elevator", this.x + x, this.y + y);
            }
        }
    }
}
