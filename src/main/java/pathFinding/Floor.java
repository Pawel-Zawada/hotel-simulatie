package pathFinding;

import java.util.ArrayList;

public class Floor {
    private int width;
    private int height;
    private ArrayList<Node> nodeList = new ArrayList<>();

    public Floor(int width, int height){
        this.height = height;
        this.width = width;

        floorBuilder();
    }

    private void floorBuilder(){
        for(int y = 0; y<this.height;y++){
            for (int x = 0; x<this.width; x++){
                nodeList.add(new Node(10,10,false,x+1,y+1));
            }
        }
    }

    public ArrayList<Node> getNodeList(){
        return nodeList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
