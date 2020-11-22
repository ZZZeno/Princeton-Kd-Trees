import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SET;

import java.util.LinkedList;

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
        if (p == null) {
            throw new IllegalArgumentException();
        }
        point2DSET.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return point2DSET.contains(p);
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        LinkedList<Point2D> pointsInRect = new LinkedList<Point2D>();
        for (Point2D p : point2DSET) {
            if (rect.contains(p)) {
                pointsInRect.add(p);
            }
        }
        return pointsInRect;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }
        Point2D temp = point2DSET.min();
        double minRange = p.distanceTo(point2DSET.min());
        for (Point2D cur : point2DSET) {
            if (p.distanceTo(cur) < minRange) {
                temp = p;
                minRange = p.distanceTo(cur);
            }
        }
        return temp;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
