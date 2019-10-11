package simulation;

/**
 * Compares rooms by classification.
 */
public class RoomComparator implements java.util.Comparator<Room> {
    @Override
    public int compare(Room o1, Room o2) {
        return o1.getClassification() - o2.getClassification();
    }
}
