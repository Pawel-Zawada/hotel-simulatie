package simulation;

import drawing.DrawHelper;

public class Room implements HotelElement{
    private int width;
    private int height;
    private int x;
    private int y;
    private int classification;

    public Room(int width, int height,int x,int y, int classification ){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.classification = classification;
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

    public int getClassification() {
        return classification;
    }

    @Override
    public void draw(DrawHelper drawHelper) {
        for(int x = 0; x < this.width; x++){
            // Also support rooms higher than 1.
            for(int y = 0; y < this.height; y++){
                if(x == 0 && y == 0) {
                    drawHelper.drawSprite("room_door", this.x + x, this.y + y);
                }else if(y == 0){
                    drawHelper.drawSprite("room_window", this.x + x, this.y + y);
                }else{
                    drawHelper.drawSprite("room_wall", this.x + x, this.y + y);
                }
            }
        }
    }
}

