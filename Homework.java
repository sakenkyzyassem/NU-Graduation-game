import java.awt.geom.AffineTransform;
import java.awt.*;

public class Homework extends Assessment{
    
    public Homework (String name, Vector2D position, State state, Common common, int points){
        super (name, position, state, common, points);
    }

    @Override
    public void draw (Graphics2D g2d){
        
        FontMetrics fontMetrics = g2d.getFontMetrics();
        Font font = g2d.getFont();
        AffineTransform transform = g2d.getTransform();

        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.translate((int)this.position.x, (int)this.position.y);

        StringBuilder pointsStr = new StringBuilder().append(this.points);
        String pntsStr = pointsStr.toString();
        
        g2d.setPaint(Color.GREEN);
        g2d.fillRect(-10, -10, 20, 20);

        g2d.setPaint(Color.BLACK);
        g2d.drawString(pntsStr, (int)(-fontMetrics.stringWidth(pntsStr) / 2.0), 5);

        g2d.setTransform(transform);
        g2d.setFont(font);
    }

}