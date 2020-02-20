public class HomeworkFactory extends AssessmentFactory {

    public HomeworkFactory (Common common) {
        super(common);
    }
    
    @Override
    public Assessment createAssessment (Vector2D position){
        Homework hw =  new Homework("Homework", position, this.common.stationary, this.common, this.common.randomInt(1, 3) );

        return hw;
    }

}
