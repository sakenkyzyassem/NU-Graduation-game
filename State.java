public abstract class State {
    public boolean isOver;
    public boolean isVisible;

    public State(boolean over, boolean visible){
        isOver = over;
        isVisible = visible;
    }

    public abstract void step(Entity e);
}