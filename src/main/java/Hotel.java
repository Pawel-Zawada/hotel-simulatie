public class Hotel {
    private Floor[] floors;

    public Hotel(int numberOfFloors){
        this.floors = new Floor[numberOfFloors];
    }

    public Floor[] getFloors(){
        return floors;
    }

}
