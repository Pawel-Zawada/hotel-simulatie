import pathFinding.Floor;
import pathFinding.PathFinding;

public class Entry {
    public static void main(String[] args){

        Floor floor = new Floor(10 ,10);
        PathFinding pathFinding = new PathFinding(floor);

        System.out.println("No matter how many times I say hello, the world never answers.");
    }
}
