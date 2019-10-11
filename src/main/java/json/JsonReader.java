package json;
import com.google.gson.Gson;
import simulation.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marc Kemp
 * A reader class for reading JSON elements from the layout file.
 */
public class JsonReader {

    public List<HotelElement> loadHotelElements(String fileName){
        return ParseJson(fileName);
    }

    /**
     * Parses the file with the given filename into a list of hotel elements.
     */
    private List<HotelElement> ParseJson(String fileName){
        ArrayList<HotelElement> hotelElements = new ArrayList<>();
        Gson gson = new Gson();

        int roomNumber = 1;
        try {
            JsonElement hotelElement = gson.fromJson(new FileReader(fileName), JsonElement.class);
            for (JsonElement.Container container : hotelElement) {

                int width = parseDimension(container.getDimension())[0];
                int height = parseDimension(container.getDimension())[1];
                int x = parseDimension(container.getPosition())[0];
                int y = parseDimension(container.getPosition())[1];

                switch (container.getAreaType()) {
                    case "Room":
                        hotelElements.add(new Room(width,height,x,y,Integer.parseInt(container.getClassification().substring(0,1)), roomNumber++));
                        break;
                    case "Restaurant":
                        hotelElements.add(new Restaurant(width,height,x,y, Integer.parseInt(container.getCapacity().substring(0,1))));
                        break;
                    case "Fitness":
                        hotelElements.add(new Gym(width,height,x,y));
                        break;
                    case "Cinema":
                        hotelElements.add(new Cinema(width,height,x,y));
                        break;
                }
            }
        }catch (Exception e){}
        return hotelElements;
    }

    /**
     * Parses a dimension string into its constituent values.
     */
    private int[] parseDimension(String string) throws Exception {
        String[]splitString = string.split("\\s*,\\s*");
        if(splitString.length!=2 ){
            throw new Exception("missing dimensions");
        }
        int[] splitStringToIn = new int[2];
        splitStringToIn[0] = Integer.parseInt(splitString[0]);
        splitStringToIn[1] = Integer.parseInt(splitString[1]);
        return splitStringToIn;
    }
}
