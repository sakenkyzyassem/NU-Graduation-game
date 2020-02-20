public class Rest extends State {
    
    private int time;

    public Rest (int time) {
        super(false, true);
        this.time = time;
    }

    @Override 
    public void step (Entity e){
        time--;

        if(time <= 0 ){
            isOver = true;
        }
    }

    @Override
    public final String toString () {
        return "Rest";
    }
}