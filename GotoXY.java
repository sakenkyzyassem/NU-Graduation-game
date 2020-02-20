public class GotoXY extends State{

    Vector2D vector;
    int speed;
    
    public GotoXY(Vector2D vector, int speed){
        super(false, true);

        this.vector = vector;
        this.speed = speed;
    }
    
    @Override
    public final void step(Entity e) {

        if(e.position.distanceTo(this.vector) < this.speed){
            e.position.set(this.vector);
        } 
        else {
            Vector2D normalizedVect = this.vector.minus(e.position).normalize();
            e.position.moveby(normalizedVect.movebySpeed(this.speed));
        }
        
        if (e.position.distanceTo(this.vector) < 1.0) {
            this.isOver = true;
        }
    }

    @Override
    public final String toString() {
        return "GotoXY";
    }


}