
import javax.swing.*;

public class NURunner extends JFrame{

    static final long serialVersionUID;
    public Display display;
    public Common common;

    private NURunner (int width, int height){
        new JFrame("NU Graduation by Assem Abikhanova");
        this.common = new Common(height, width);
        this.display = new Display(this.common);
        
        add(this.display);
        setLocationByPlatform(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    public static void main(String[] args){
        int width = 1200;
        int height = 600;
        final NURunner nuRunner = new NURunner(width, height);
        
        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    nuRunner.setVisible(true);
                }
            }
        );
        
        try{
            while (true) {
                    nuRunner.common.stepAllEntities();
                    nuRunner.display.repaint();
                
                    Thread.sleep(60);
            }
        }
        catch (InterruptedException e){
            System.out.println("Interrupted exception");
            e.printStackTrace();
        }
    }

    static{
        serialVersionUID = 1;
    }
}