public class Stationary extends State{
    
    public Stationary() {
        super(false, true);
    }
    
    @Override
    public final void step(final Entity e) { }

    @Override
    public final String toString (){
        return "Stationary";
    }

}