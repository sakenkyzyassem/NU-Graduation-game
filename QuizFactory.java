public class QuizFactory extends AssessmentFactory {

    public QuizFactory (Common common) {
        super(common);
    }
    
    @Override
    public Assessment createAssessment (Vector2D position){
        Quiz quiz =  new Quiz("Quiz", position, this.common.stationary, this.common, this.common.randomInt(3, 5) );

        return quiz;
    }
}