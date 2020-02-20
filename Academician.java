import java.awt.geom.AffineTransform;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


public class Academician extends Entity{

    BufferedImage image;
    final int imageHeight;
    final int imageWidth;

    public Academician (String name, Vector2D position, State state, Common common, String path ){
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
    
        this.imageHeight = (this.image.getHeight() / 4);
        this.imageWidth = (this.image.getWidth() / 4);
    }

    public Assessment createAssessment(){
        AssessmentFactory factories = common.factories.get(common.randomInt(0, common.factories.size() - 1) );
    
        return factories.createAssessment(common.vector());
    }

    @Override
    public void draw (Graphics2D graphics2D) {
        
        Font font = graphics2D.getFont();
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        AffineTransform transform = graphics2D.getTransform();

        graphics2D.setFont(new Font("Arial", Font.BOLD, 14));
        graphics2D.translate((int) this.position.x, (int)this.position.y);

        graphics2D.drawImage(this.image, -(this.imageWidth / 2), -(this.imageHeight / 2), this.imageWidth, this.imageHeight, null);
        
        String academicianName = this.name;
        String stateString = this.state.toString();

        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawString(academicianName, (int)(-fontMetrics.stringWidth(academicianName) / 2.0) + 1, -this.imageHeight / 2 - 5);
        graphics2D.drawString(stateString, (int)(-fontMetrics.stringWidth(stateString) / 2.0) + 1, this.imageHeight / 2 + 15 );
        
        graphics2D.setTransform(transform);
        graphics2D.setFont(font);
    }
}