public class Point2D {
    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double distanceTo(Point2D that) {
        return 0.0;
    }

    public double distanceSquaredTo(Point2D that) {
        return 0.0;
    }

    public int compareTo(Point2D that) {
        return 1;
    }

    public boolean equals(Object that) {
        return false;
    }

    public void draw() {

    }

    public String toString() {
        return "";
    }
}
