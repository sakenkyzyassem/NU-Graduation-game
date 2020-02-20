import java.awt.geom.AffineTransform;
import java.awt.*;

public class Lab extends Assessment{
    
    public Lab (String name, Vector2D position, State state, Common common, int points){
        super (name, position, state, common, points);
    }

    @Override
    public final void draw (Graphics2D g2d) {
        
        final FontMetrics fontMetrics = g2d.getFontMetrics();
        final AffineTransform transform = g2d.getTransform();
        final Font font = g2d.getFont();

        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.translate((int)this.position.x, (int)this.position.y);

        StringBuilder pointsStr = new StringBuilder().append(this.points);
        String pntsStr = pointsStr.toString();

        g2d.setPaint(Color.RED);
        g2d.fillOval(-10, -10, 20, 20);

        g2d.setPaint(Color.BLACK);
        g2d.drawString(pntsStr, (int)(-fontMetrics.stringWidth(pntsStr) / 2.0), 6);
        
        g2d.setTransform(transform);
        g2d.setFont(font);
    }
}