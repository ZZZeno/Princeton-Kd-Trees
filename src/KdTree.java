import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.LinkedList;

public class KdTree {
    private final SET<Point2D> pointKdTree = new SET<Point2D>();

    //    private final RedBlackBST<>
    // construct an empty set of points
    public KdTree() {

    }

    // is the set empty?
    public boolean isEmpty() {
        return pointKdTree.isEmpty();
    }

    // number of points in the set
    public int size() {
        return pointKdTree.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        pointKdTree.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return pointKdTree.contains(p);
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
        
        return null;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
