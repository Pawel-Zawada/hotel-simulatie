package assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileAssetLoader implements AssetLoader {

    public Image loadSprite(String name) throws IOException {
        var imageFile = new File("assets/sprites/" + name + ".png");
        var image = ImageIO.read(imageFile);
        return image;
    }

    public Image[] loadSpriteSet(String name) throws IOException {
        var assetDirectory = new File("assets/sprites");

        var variants = Arrays.stream(assetDirectory.listFiles())
                .filter(f -> f.getName().startsWith(name))
                .sorted()
                .collect(Collectors.toList());

        Image[] images = new Image[variants.size()];
        for(int i = 0; i < variants.size(); i++){
            images[i] = ImageIO.read(variants.get(i));
        }

        return images;
    }

    @Override
    public Font loadFont(String name) throws IOException, FontFormatException {
        var fontFile = new File("assets/fonts/" + name + ".ttf");
        var font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        return font;
    }
}

