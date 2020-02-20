
import java.awt.*;
import javax.swing.JPanel;

public class Display extends JPanel{

    public Common common;
    private UniversityMap map;

    public Display (final Common common){
        this.common = common;
        this.setBackground(Color.WHITE);
        this.map = new UniversityMap("Demo/NUMap-Faded.jpg", "NU", new Vector2D(0.0, 0.0), new Stationary(), common);
        
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(this.common.windowWidth, this.common.windowHeight);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D)g;

        this.map.draw(graphics2D);
        this.common.drawAllEntities(graphics2D);
    }
}