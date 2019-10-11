package assets;

import java.awt.*;
import java.io.IOException;

public interface AssetLoader {
    Image loadSprite(String name) throws IOException;

    Font loadFont(String name) throws IOException, FontFormatException;
}