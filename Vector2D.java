public class Vector2D{
    public double x;
    public double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public void moveby(Vector2D other){
        this.x += other.x;
        this.y += other.y;
    }

    public double distanceTo(Vector2D other) { 
        return Math.hypot((this.x - other.x), (this.y - other.y));
    }

    public Vector2D normalize() {
        double length = Math.hypot(this.x, this.y);
        return new Vector2D(this.x / length, this.y / length);
    }

    public Vector2D plus(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D minus (Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D movebySpeed (int speed){
        return new Vector2D(this.x * speed, this.y * speed);
    }

}