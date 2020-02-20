import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class UniversityMap extends Entity {

    private BufferedImage image;

    public UniversityMap (String path, String name, Vector2D position, State state, Common common){
        super(name, position, state, common);

        try {
            this.image = ImageIO.read(new File(path));
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file path");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Problem reading the file");
            e.printStackTrace();
        }
    }

    @Override
    public void draw (Graphics2D graphics2D) {
        graphics2D.drawImage(this.image, 0, 0, common.windowWidth, common.windowHeight, null);
    }
}