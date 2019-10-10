package json;
import com.google.gson.Gson;
import simulation.*;
import java.io.FileReader;
import java.util.ArrayList;

public class JsonReader {

    public ArrayList<HotelElement> loadHotelElements(String fileName){
        return parseJason(fileName);
    }

    private ArrayList<HotelElement> parseJason(String fileName){
        ArrayList<HotelElement> hotelElements = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JsonElement hotelElement = gson.fromJson(new FileReader(fileName), JsonElement.class);
            for (JsonElement.Container container : hotelElement) {

                int width = getIntFromContainer(container.getDimension())[0];
                int height = getIntFromContainer(container.getDimension())[1];
                int x = getIntFromContainer(container.getPosition())[0];
                int y = getIntFromContainer(container.getPosition())[1];

                switch (container.getAreaType()) {
                    case "Room":
                        hotelElements.add(new Room(width,height,x,y,Integer.parseInt(container.getClassification().substring(0,1))));
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

    private int[] getIntFromContainer(String string) throws Exception {
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
