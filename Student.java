import java.awt.geom.AffineTransform;
import java.awt.*;

public class Student extends Entity{
    
    public int grade;

    public Student(String name, Vector2D position, State state, Common common){
        super(name, position, state, common);
    }

    @Override
    public void draw(Graphics2D g2d){

        final FontMetrics fontMetrics = g2d.getFontMetrics();
        final AffineTransform transform = g2d.getTransform();
        final Font font = g2d.getFont();

        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.translate((int)this.position.x, (int)this.position.y);
        if (this.grade >= 100) {
            g2d.setPaint(Color.MAGENTA);
        }
        else {
            g2d.setPaint(Color.CYAN);
        }

        g2d.fillOval(-15, -15, 30, 30);
        g2d.drawOval(-15, -15, 30, 30);
        
        final String studentName = this.name;
        final String gradeString = new StringBuilder().append(this.grade).toString();
        final String stateString = this.state.toString();
        
        g2d.setPaint(Color.BLACK);
        g2d.drawString(studentName, (int)(-fontMetrics.stringWidth(studentName) / 2.0) + 1, -18);
        g2d.drawString(gradeString, (int)(-fontMetrics.stringWidth(gradeString) / 2.0) + 1, 6);
        g2d.drawString(stateString, (int)(-fontMetrics.stringWidth(stateString) / 2.0) + 1, 30);
        
        g2d.setTransform(transform);
        g2d.setFont(font);
    }


}