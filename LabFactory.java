public class LabFactory extends AssessmentFactory{
    
    public LabFactory (Common common) {
        super(common);
    }
    
    @Override
    public Assessment createAssessment (Vector2D position){
        Lab lab =  new Lab("Lab", position, this.common.stationary, this.common, this.common.randomInt(2, 4) );

        return lab;
    }
}