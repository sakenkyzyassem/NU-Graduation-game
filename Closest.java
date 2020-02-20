public class Closest extends State{

    private int time;
    private int speed;

    public Closest (int time, int speed) {
        super(false, true);

        this.time = time;
        this.speed = speed;
    }

    @Override 
    public void step (Entity e){
        Assessment assessment = null;
        double closest = Double.MAX_VALUE;
        
        for (Assessment assessment2 : e.common.assessments) {
            double dist = e.position.distanceTo(assessment2.position);

            if (dist < closest) {
                assessment = assessment2;
                closest = dist;
            }
        }
        
        if(assessment != null) {
            e.state = new GotoXY(assessment.position, this.speed);
        }

        if(--this.time <= 0){
            this.isOver = true;
        }
    }

    @Override
    public final String toString(){
        return "Closest";
    }
}