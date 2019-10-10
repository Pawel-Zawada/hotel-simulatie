package pathfinding;
import org.junit.Test;
import simulation.*;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


public class GraphTest {

    @Test
    public void createGraph_testHotel_CreatesSingleNodeForElevator(){
        ArrayList<HotelElement> hotelElements = new ArrayList<>();
        hotelElements.add(new Elevator(1,2,0,0));
        hotelElements.add(new Stairs(1,1,2,0));
        hotelElements.add(new Stairs(1,1,2,1));
        hotelElements.add(new CheckInDesk(1,1,1,0));
        hotelElements.add(new Room(1,1,1,1,1, 1));
        Hotel hotel = new Hotel(hotelElements,3,2);
        var graph = Graph.createGraph(hotel);

        var elevator = graph.getNodeList().stream().filter(e->e.getElement().getClass()==Elevator.class).findFirst().get();
        assertThat(elevator.getNeighbours().stream().map(n->n.getNode().getElement())).containsExactlyInAnyOrder(hotelElements.get(3), hotelElements.get(4));

        var elevatorNodeCount = graph.getNodeList().stream().filter(n -> n.getElement().getClass() == Elevator.class).count();
        assertThat(elevatorNodeCount).isEqualTo(1);
    }

    @Test
    public void createGraph_testHotel_CreatesCorrectNumberOfStairNodes() {
        ArrayList<HotelElement> hotelElements = new ArrayList<>();
        hotelElements.add(new Elevator(1, 2, 0, 0));
        hotelElements.add(new Stairs(1, 1, 2, 0));
        hotelElements.add(new Stairs(1, 1, 2, 1));
        hotelElements.add(new CheckInDesk(1, 1, 1, 0));
        hotelElements.add(new Room(1, 1, 1, 1, 1, 1));
        Hotel hotel = new Hotel(hotelElements, 3, 2);
        var graph = Graph.createGraph(hotel);

        var stairNodeCount = graph.getNodeList().stream().filter(n -> n.getElement().getClass() == Stairs.class).count();
        assertThat(stairNodeCount).isEqualTo(2);
    }
}