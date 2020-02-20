import java.awt.*;
import java.util.*;
import java.awt.geom.AffineTransform;

public class Common {
    final public int windowWidth;
    final public int windowHeight;
    public ArrayList <Academician> academicians;
    public ArrayList <Speaker> speakers;
    public ArrayList <Student> students;
    public ArrayList <Assessment> assessments;
    public ArrayList <AssessmentFactory> factories;
    final public State stationary;
    private Random random;
    final public Vector2D gatheringPosition;
    private String [] studentsName;
    public boolean isAllFinished;

    public Common(int windowHeight, int windowWidth){
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.stationary = new Stationary();
        this.academicians = new ArrayList<>();
        this.speakers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.assessments = new ArrayList<>();
        this.factories = new ArrayList<>();
        this.random = new Random();
        this.gatheringPosition = new Vector2D(this.windowWidth * 563 / 800, this.windowHeight * 327 / 400);
        
        this.factories.add(new LabFactory(this));
        this.factories.add(new QuizFactory(this));
        this.factories.add(new HomeworkFactory(this));

        this.academicians.add(new Academician("Katsu", this.vector(5), this.stateAcademician(), this, "Demo/ShigeoKatsu.gif"));
        this.academicians.add(new Academician("Tourassis", this.vector(5), this.stateAcademician(), this, "Demo/VassiliosTourassis.gif"));
        this.academicians.add(new Academician("Nivelle", this.vector(5), this.stateAcademician(), this, "Demo/HansDeNivelle.gif"));
        this.academicians.add(new Academician("Selim", this.vector(5), this.stateAcademician(), this, "Demo/SelimTemizer.gif"));
        
        this.speakers.add(new Speaker("Nazarbayev", this.gatheringPosition.plus(new Vector2D(100.0, 0.0)), this.stationary, this, "Demo/NursultanNazarbayev.gif"));
        this.speakers.add(new Speaker("Tokayev", this.gatheringPosition.minus(new Vector2D(100.0, 0.0)), this.stationary, this, "Demo/KassymJomartTokayev.gif"));
        
        this.studentsName = new String[] {"Assem", "Kamila", "Akbope", "Venera", "Alua", "Assemgul", "Akhida", "Aruzhan", "Leila", "Khadisha"};

        for(String name: this.studentsName){
            students.add(new Student(name, vector(10), this.stateStudent(), this));
        }

        this.isAllFinished = false;
    }

    public int randomInt(int from, int to){
        return this.random.nextInt( to - from + 1) + from;
    }

    public double randomDouble(int from, int to){
        double d = this.random.nextDouble();
        return from + (to - from) * d;
    }

    private Vector2D vector(int a){
        double x = randomDouble(a, windowWidth - a);
        double y = randomDouble(a, windowHeight - a);

        return new Vector2D(x, y);
    }

    public Vector2D vector(){
        return new Vector2D(this.randomInt(-20, 20), this.randomInt(-20, 20));
    }

    private State stateAcademician(){
        int r = this.randomInt(1, 3);

        if (r == 1)
            return new Rest(this.randomInt(10, 20));
        else if (r == 2)
            return new ZigZag(this.randomInt(10, 20), this.randomInt(2, 8), vector().normalize());
        else if (r == 3)
            return new GotoXY(vector(5), this.randomInt(2, 8));
        else{
            System.err.println("Error finding a state for academician");
            return null;
        }
    }

    private State stateStudent(){
        int r = this.randomInt(1, 4);

        if (r == 1)
            return new Rest(this.randomInt(10, 20));
        else if (r == 2)
            return new ZigZag(this.randomInt(10, 20), this.randomInt(2, 8), vector().normalize());
        else if (r == 3)
            return new GotoXY(vector(5), this.randomInt(2, 8));
        else if (r == 4)
            return new Closest(this.randomInt(10, 20), this.randomInt(2, 8));
        else{
            System.err.println("Error finding a state for academician");
            return null;
        }
    }

    public void stepAllEntities() {
        
        for (Academician academ: academicians) {

            academ.step();

            if (!((academ.state.toString()).equals("Rest")) && !((academ.state.toString()).equals("Stationary")) && this.randomInt(1, 8) == 1) {
                
                Common common2 = academ.common;
                Vector2D vect2 = academ.position;

                AssessmentFactory factory = common2.factories.get(common2.randomInt(0, common2.factories.size() - 1));
                
                int x = (int)vect2.x + ((common2.randomInt(0, 1) == 0) ? -1 : 1) * common2.randomInt(10, 20);
                int y = (int)vect2.y + ((common2.randomInt(0, 1) == 0) ? -1 : 1) * common2.randomInt(10, 20);

                if (x <= 0) {
                    x = 10;
                }
                else if (x >= common2.windowWidth) {
                    x = common2.windowWidth - 10;
                }

                if (y <= 0) {
                    y = 10;
                }
                else if (y >= common2.windowHeight) {
                    y = common2.windowHeight - 10;
                }

                Assessment newaAssessment = factory.createAssessment(new Vector2D(x, y));
                assessments.add(newaAssessment);
            }
            if(academ.state.isOver){
                academ.state = this.stateAcademician();
            }
        }
        
        for (Student stud: students) {

            stud.step();

            if (stud.grade < 100) {
                synchronized (assessments) {    
                    for (int i = assessments.size() -1; i >= 0; --i) {
                        Assessment assess = assessments.get(i);

                        if ( stud.position.distanceTo(assess.position) <= 20.0) {
                            stud.grade += assess.points;
                            this.assessments.remove(assess);
                        }
                    }
                }
            }
            if (stud.state.isOver) {
                if (stud.grade < 100) {
                    stud.state = this.stateStudent();
                }
                else if (stud.position.distanceTo(this.gatheringPosition) < 10.0){
                    stud.state = this.stationary;
                }
                else {
                    stud.state = new GotoXY(this.gatheringPosition, this.randomInt(2, 5));
                }
            }
        }

        boolean isFinished = true;

        for(Student stud: students) {
            if (stud.grade < 100) {
                isFinished = false;
                break;
            }
        }
        if (isFinished) {
            this.assessments.clear();
            int i = 0;
            for (Academician acad: academicians) {
                Vector2D finalPos = new Vector2D(gatheringPosition.x - (2-i)*100 + 50, gatheringPosition.y - 100);
                acad.state = new GotoXY(finalPos, this.randomInt(2, 5));
                if(acad.position.distanceTo(finalPos) < 1.0) {
                    acad.state = this.stationary;
                }
                i++;
            }
        }
        this.isAllFinished = isFinished;
    }

    public void drawAllEntities(Graphics2D g2d){
        synchronized( assessments ) {
            for ( Assessment assess : assessments )  { assess.draw( g2d ) ; }
        }
        for(Student stud: students){
            stud.draw(g2d);
        }
        for(Academician acad: academicians) {
            acad.draw(g2d);
        }
        if(this.isAllFinished){
            for(Speaker speaker: speakers){
                speaker.draw(g2d);
            }
            FontMetrics fontMetrics = g2d.getFontMetrics();
            AffineTransform transform = g2d.getTransform();
            Font font = g2d.getFont();

            String str = "Graduation Ceremony";

            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            g2d.translate((int)this.gatheringPosition.x, (int)this.gatheringPosition.y + 80);
            g2d.setPaint(Color.BLACK);
            g2d.drawString(str, (int)(-fontMetrics.stringWidth(str) / 2.0), 0);
            
            g2d.setTransform(transform);
            g2d.setFont(font);
        }
    }
}