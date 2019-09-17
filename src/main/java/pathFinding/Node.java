package pathFinding;

public class Node {
    private int height;
    private int width;
    private boolean isSolid;

    public Node(int width,int height, boolean isSolid){
        this.width = width;
        this.height = height;
        this.isSolid = isSolid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isSolid() {
        return isSolid;
    }
}
