public class ZigZag extends State{
    
    private int time;
    private int speed;
    private Vector2D vector;

    public ZigZag (int time, int speed, Vector2D vector) {
        super(false, true);
        
        this.time = time;
        this.speed = speed;
        this.vector = vector;
    }

    @Override 
    public void step (Entity e) {
        e.position.moveby(this.vector.movebySpeed(this.speed));

        if (e.position.x <= 0.0 || e.common.windowWidth <= e.position.x) {
            this.vector.x = -this.vector.x;
        }

        if (e.position.y <= 0.0 || e.common.windowHeight <= e.position.y) {
            this.vector.y = -this.vector.y;
        }

        if (--this.time <= 0) {
            this.isOver = true;
        }
    }
    
    @Override
    public final String toString() {
        return "ZigZag";
    }
}