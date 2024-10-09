public class Starship {
    private String name;
    private Vector position;
    private Vector velocity = new Vector(0,0);
    public Starship(String name, Vector position){
        this.name = name;
        this.position = position;
    }
    public String getName() {
        return this.name;
    }
    public Object getPosition() {
        return this.position;
    }
    public Object getVelocity() {
        return this.velocity;
    }
    @Override
    public String toString() {
        return this.name + " at " + this.position + " moving " + this.velocity;
    }
    public void accelerate(Vector vel) {
        this.velocity.setX((int) (this.velocity.getX() + vel.getX()));
        this.velocity.setY((int) (this.velocity.getY() + vel.getY()));
    }
    public void drift() {
        this.position.setX((int) (this.velocity.getX() + this.position.getX()));
        this.position.setY((int) (this.velocity.getY() + this.position.getY()));
    }
}
