package pathFinding;

public class Node {
    private int height;
    private int width;
    private boolean isSolid;
    private int x;
    private int y;
    private double fCost;
    private Node parrentNode;

    public Node(int width,int height, boolean isSolid, int x, int y){
        this.width = width;
        this.height = height;
        this.isSolid = isSolid;
        this.x=x;
        this.y=y;
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

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public double getFCost() {
        return fCost;
    }


    public void setFCost(double fCost){
        this.fCost = fCost;
    }

    public void setParrentNode(Node parrentNode) {
        this.parrentNode = parrentNode;
    }

    public Node getParrentNode() {
        return parrentNode;
    }
}
