package pathfinding;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PathFindingTest {
    @Test
    public void doPathFinding_smallSquare_findsPath(){
        PathFinding pathFinding = new PathFinding();
        Node[] nodes = new Node[25];

        for(int i  = 0 ; i<25; i++){
            int _x = i%5;
            int _y = i/5;
            nodes[i] = new Node(_x,_y);
        }
        for(int x = 0; x<5; x++){
            for(int y= 0; y<5; y++){
                Node currentNode = nodes[y*5+x];
                if(x>0){
                    currentNode.connect(nodes[(y*5+x)-1], 1);
                }
                if(x<4){
                    currentNode.connect(nodes[(y*5+x)+1], 1);
                }
                if(y>0){
                    currentNode.connect(nodes[((y-1)*5+x)], 1);
                }
                if(y<4){
                    currentNode.connect(nodes[((y+1)*5+x)], 1);
                }
            }
        }
        Node path = pathFinding.doPathFinding(nodes[0],nodes[22]);
        assertThat(path.getgCost()).isEqualTo(6);
    }
}