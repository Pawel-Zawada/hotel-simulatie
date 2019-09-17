package pathFinding;

import java.util.ArrayList;

public class TestFloor {
    private int width;
    private int height;
    private ArrayList<Node> nodeList = new ArrayList<>();

    public TestFloor(int width, int height){
        this.height = height;
        this.width = width;
    }

    private void floorBuilder(){
        for(int y = 0; y<this.height;y++){
            for (int x = 0; x<this.width; x++){
                nodeList.add(new Node(10,10,false));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
