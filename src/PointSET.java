import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointSET {
    private final SET<Point2D> point2DSET = new SET<Point2D>();
//    private final RedBlackBST<>
    // construct an empty set of points
    public PointSET() {
        
    }

    // is the set empty?
    public boolean isEmpty() {
        return point2DSET.isEmpty();
    }

    // number of points in the set
    public int size() {
        return point2DSET.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return point2DSET.contains(p);
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
//    public Iterable<Point2D> range(RectHV rect) {
//
//    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        return p;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
