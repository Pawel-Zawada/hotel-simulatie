package json;

import java.util.ArrayList;

/**
 * @author Marc Kemp
 */
public class JsonElement  extends ArrayList<JsonElement.Container> {

    public class Container {
        private String AreaType;
        private Object object;
        private String Position;
        private String Dimension;
        private String Classification;
        private String Capacity;

        public String getAreaType() {
            return AreaType;
        }

        public Object getObject() {
            return object;
        }

        public String getPosition() {
            return Position;
        }

        public String getClassification() {
            return Classification;
        }

        public String getDimension() {
            return Dimension;
        }

        public String getCapacity(){
            return Capacity;
        }
    }
}
