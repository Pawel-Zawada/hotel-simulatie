
package simulation;

public class Cinema implements HotelElement, IObserver {
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

    public void observe(){
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

    /*public int getCinemaID(){
        return cinemaID;
    }*/

    public boolean isWalkable() {
        return false;
    }
}

