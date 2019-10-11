
package simulation;

import drawing.DrawHelper;

public class Cinema implements HotelElement, HteObserver {
    private int width;
    private int height;
    private int x;
    private int y;
  //  private int cinemaID;

    public Cinema(int width, int height, int x, int y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void observeHTE(){
        //start playing a movie
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
        for(int x = 0; x < this.width; x++){
            // Also support rooms higher than 1.
            for(int y = 0; y < this.height; y++){
                if(y == 0){
                    drawHelper.drawSprite("cinema", this.x + x, this.y + y);
                }else{
                    drawHelper.drawSprite("cinema_background", this.x + x, this.y + y);
                }
            }
        }
    }
}

