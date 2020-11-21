import javax.sound.midi.Track;

public class Point2D implements Comparable<Point2D> {
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
        if (that == null) {
            throw new IllegalArgumentException();
        }
        return Math.sqrt(Math.pow(this.x()-that.x(), 2)+Math.pow(this.y()-that.y(), 2));
    }

    public double distanceSquaredTo(Point2D that) {
        if (that == null) {
            throw new IllegalArgumentException();
        }
        return Math.pow(this.x()-that.x(), 2)+Math.pow(this.y()-that.y(), 2);
    }

    public int compareTo(Point2D that) {
        if (that == null) {
            throw new IllegalArgumentException();
        }
        if (this.equals(that)) {
            return 0;
        }
        if (this.y() > that.y()) {
            return 1;
        }
        if (this.x() > that.x()) {
            return 1;
        }
        return -1;
    }

    public boolean equals(Object that) {
        if (that == null) {
            throw  new IllegalArgumentException();
        }
        if (this.getClass() != that.getClass()) {
            return false;
        }
        Point2D tempThat = (Point2D) that;
        return this.x() == tempThat.x() && this.y() == tempThat.y();
    }

    public void draw() {

    }

    public String toString() {
        return String.format("(%s, %s)", this.x(), this.y());
    }

    public static void main(String[] args) {
        Point2D x = new Point2D(1, 1);
        Point2D base = new Point2D(0, 0);
        System.out.println(x);
        System.out.println(x.distanceSquaredTo(base));
        System.out.println(x.distanceTo(base));
    }
}
