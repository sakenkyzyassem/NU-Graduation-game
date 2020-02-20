import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.geom.AffineTransform;

public class Speaker extends Entity{

    BufferedImage image;
    int imageHeight;
    int imageWidth;
    
    public Speaker (String name, Vector2D position, State state, Common common, String path){
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
    
        this.imageHeight = this.image.getHeight() / 4;
        this.imageWidth = this.image.getWidth() / 4;
    }

    @Override
    public final void draw(final Graphics2D graphics2D) {
        
        final Font font = graphics2D.getFont();
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        final AffineTransform transform = graphics2D.getTransform();

        graphics2D.setFont(new Font("Arial", Font.BOLD, 14));
        graphics2D.translate((int)this.position.x, (int)this.position.y);

        final String speakerName= this.name;
        
        graphics2D.drawImage(this.image, -this.imageWidth / 2, -this.imageHeight / 2, this.imageWidth, this.imageHeight, null);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawString(speakerName, (int)(-fontMetrics.stringWidth(speakerName) / 2.0) + 1, -this.imageHeight / 2 - 2);
        
        graphics2D.setTransform(transform);
        graphics2D.setFont(font);
    }

}