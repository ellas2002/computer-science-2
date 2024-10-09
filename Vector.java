import java.util.Objects;
//Ella Stiller
public class Vector {
    double X;
    double Y;
    public double getX() {return X;}
    public double getY() {return Y;}
    public void setX(int x) {this.X = x;}
    public void setY(int y) {this.Y = y;}
    public Vector(double x, double y){
        this.X = x;
        this.Y = y;
    }
    public void gettersAndSettersWork(){
        this.X = X;
        this.Y = Y;
    }
    public Vector() {
        this.X = 0;
        this.Y = 0;
    }

    public String toString() {
        return "<" +
                + X +
                ", " + Y +
                ">";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.X, X) == 0 && Double.compare(vector.Y, Y) == 0;
    }

    public int hashCode() {
        return Objects.hash(X, Y);
    }

    public Vector plus(Vector b) {
        return new Vector(this.X + b.X, this.Y + b.Y);
    }
    public Vector minus(Vector b) {

        return new Vector(this.X - b.X, this.Y - b.Y);
    }
    public double dot(Vector b) {
        return this.X * b.X + this.Y * b.Y;
    }
    public Object times(int i) {
        return new Vector(this.X * i, this.Y * i);
    }


    public double distanceTo(Vector b) {
        return Math.sqrt(Math.pow(b.X - this.X, 2) + Math.pow(b.Y - this.Y, 2));
    }
}