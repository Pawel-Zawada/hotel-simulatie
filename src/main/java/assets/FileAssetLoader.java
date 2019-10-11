package assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Johan Geluk
 */
public class FileAssetLoader implements AssetLoader {
    @Override
    public Image loadSprite(String name) throws IOException {
        var imageFile = new File("assets/sprites/" + name + ".png");
        var image = ImageIO.read(imageFile);
        return image;
    }

    @Override
    public Font loadFont(String name) throws IOException, FontFormatException {
        var fontFile = new File("assets/fonts/" + name + ".ttf");
        var font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        return font;
    }
}

