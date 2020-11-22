import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;

public class KdTree {
    private final SET<Point2D> pointKdTree = new SET<Point2D>();
    private Node root;
    private int size;

    private class Node {
        private final Point2D point;
        private boolean horizontal;
        private RectHV rect;
        private Node lower;   // left or bottom
        private Node higher;  // right or top

        public Node(Point2D point, boolean parentRotation, RectHV rect) {
            this.point = point;
            this.horizontal = !parentRotation;
            this.rect = rect;
        }

        public boolean lowerThan(Node p) {
            return point.x() < p.point.x() || point.y() < p.point.y();
        }

        public boolean lowerThan(Point2D p) {
            return point.x() < p.x() || point.y() < p.y();
        }

    }

    //    private final RedBlackBST<>
    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            root = new Node(p, true, new RectHV(0, 0, 1, 1));
            size++;
            return;
        }
        Node node = new Node(p, true, new RectHV(0, 0, 1, 1));
        Node cur = root;
        while (cur != null) {
            node.horizontal = !cur.horizontal;
            System.out.println(node.point);
            if (!cur.lowerThan(p)) {
                if (cur.lower == null) {
                    cur.lower = node;
                    if (!node.horizontal) {
                        node.rect = new RectHV(cur.rect.xmin(), cur.rect.ymin(), cur.rect.xmax(), cur.point.y());
                    } else {
                        node.rect = new RectHV(cur.rect.xmin(), cur.rect.ymin(), cur.point.x(), cur.rect.ymax());
                    }
//                    if (node.point.x() < cur.point.x()) {  // right
//                        node.rect = new RectHV(cur.point.x(), cur.rect.ymin(), cur.rect.xmax(), cur.rect.ymax());
//                    } else {  // top
//                        node.rect = new RectHV(cur.rect.xmin(), cur.point.y(), cur.rect.xmax(), cur.rect.ymax());
//                    }
                    cur = null;
                } else {
                    cur = cur.lower;
                }
            } else {
                if (cur.higher == null) {
                    cur.higher = node;
                    if (node.horizontal) {
                        node.rect = new RectHV(cur.point.x(), cur.rect.ymin(), cur.rect.xmax(), cur.rect.ymax());
                    } else {
                        node.rect = new RectHV(cur.rect.xmin(), cur.point.y(), cur.rect.xmax(), cur.rect.ymax());
                    }
//                    if (node.point.x() < cur.point.x()) {  // left
//                        node.rect = new RectHV(cur.rect.xmin(), cur.rect.ymin(), cur.point.x(), cur.rect.ymax());
//                    } else {  // bottom
//                        node.rect = new RectHV(cur.rect.xmin(), cur.rect.ymin(), cur.rect.xmax(), cur.point.y());
//                    }
                    cur = null;
                } else {
                    cur = cur.higher;
                }
            }
        }
        size++;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Node cur = root;
        while (cur != null) {
            if (cur.point.equals(p)) {
                return true;
            }
            if (cur.lowerThan(p)) {
                cur = cur.lower;
            } else {
                cur = cur.higher;
            }
        }
        return false;
    }

    public void draw() {
        draw(root);
    }

    // draw all points to standard draw
    private void draw(Node node) {
        if (node != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            node.point.draw();
            StdDraw.setPenRadius(0.005);
            if (!node.horizontal) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
            }
            draw(node.lower);
            draw(node.higher);
        }
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
        KdTree kd;
        kd = new KdTree();
        kd.insert(new Point2D(0.7, 0.2));
        kd.insert(new Point2D(0.5, 0.4));
        kd.insert(new Point2D(0.2, 0.3));
        kd.insert(new Point2D(0.4, 0.7));
        kd.insert(new Point2D(0.9, 0.6));
        kd.draw();
//        Point2D temp = new Point2D(0.3, 0.5);
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(0.01);
//        temp.draw();
    }
}
