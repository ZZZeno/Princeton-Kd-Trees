import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;

    private static class Node {

        private final Point2D p;
        private Node left;
        private Node right;
        private final boolean rotation;
        private final RectHV rect;

        private final ArrayList<Point2D> same = new ArrayList<Point2D>();

        public Node(Point2D p) {
            this(p, true, 0, 1, 0, 1);
        }


        public Node(Point2D p, boolean rotation, double xmin, double xmax, double ymin, double ymax) {
            this.p = p;
            this.rotation = rotation;
            rect = new RectHV(xmin, ymin, xmax, ymax);
        }
    }

    private Point2D currentNearest;
    private double min;

    public KdTree() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int compare(Point2D p, Node n) {
        if (n.rotation) {
            if (Double.compare(p.x(), n.p.x()) == 0) {
                return Double.compare(p.y(), n.p.y());
            } else {
                return Double.compare(p.x(), n.p.x());
            }
        } else {
            if (Double.compare(p.y(), n.p.y()) == 0) {
                return Double.compare(p.x(), n.p.x());
            } else {
                return Double.compare(p.y(), n.p.y());
            }
        }
    }

    private Node generateNode(Point2D p, Node parent) {
        int cmp = compare(p, parent);
        if (cmp < 0) {
            if (parent.rotation) {
                return new Node(p, !parent.rotation, parent.rect.xmin(), parent.p.x(), parent.rect.ymin(), parent.rect.ymax());
            } else {
                return new Node(p, !parent.rotation, parent.rect.xmin(), parent.rect.xmax(), parent.rect.ymin(), parent.p.y());
            }
        } else {
            if (parent.rotation) {
                return new Node(p, !parent.rotation, parent.p.x(), parent.rect.xmax(), parent.rect.ymin(), parent.rect.ymax());

            } else {
                return new Node(p, !parent.rotation, parent.rect.xmin(), parent.rect.xmax(), parent.p.y(), parent.rect.ymax());

            }
        }
    }

    public void insert(Point2D p) {
        checkOption(p);
        if (root == null) {
            size++;
            root = new Node(p);
        } else {
            insert(p, root);
        }
    }

    private void insert(Point2D p, Node node) {
        int cmp = compare(p, node);
        if (cmp < 0) {
            if (node.left == null) {
                size++;
                node.left = generateNode(p, node);
            } else {
                insert(p, node.left);
            }
        } else if (cmp > 0) {
            if (node.right == null) {
                size++;
                node.right = generateNode(p, node);
            } else {
                insert(p, node.right);
            }
        }
    }


    public boolean contains(Point2D p) {
        checkOption(p);
        if (root == null) {
            return false;
        } else {
            return contains(p, root);
        }
    }

    private boolean contains(Point2D p, Node node) {
        if (node == null) {
            return false;
        } else if (p.equals(node.p)) {
            return true;
        } else {
            if (compare(p, node) < 0) {
                return contains(p, node.left);
            } else {
                return contains(p, node.right);
            }
        }
    }

    public void draw() {
        StdDraw.clear();
        draw(root);
    }

    private void draw(Node node) {
        if (node != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            node.p.draw();
            if (node.rotation) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            }
            // 递归画
            draw(node.left);
            draw(node.right);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }
        return new ArrayList<Point2D>(range(rect, root));
    }

    private ArrayList<Point2D> range(RectHV rect, Node node) {
        ArrayList<Point2D> list = new ArrayList<Point2D>();
        // A subtree is searched only if it might contain a point contained in the query rectangle.
        if (node != null && rect.intersects(node.rect)) {
            list.addAll(range(rect, node.left));
            list.addAll(range(rect, node.right));
            if (rect.contains(node.p)) {
                list.add(node.p);
            }
        }
        return list;
    }

    private void checkOption(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
    }

    public Point2D nearest(Point2D p) {
        checkOption(p);
        if (isEmpty()) {
            return null;
        }
        currentNearest = null;
        min = Double.POSITIVE_INFINITY;
        findNearest(p, root);
        return currentNearest;
    }

    private void findNearest(Point2D p, Node node) {
        if (node == null) {
            return;
        }
        // The square of the Euclidean distance between the point {@code p} and the closest point on this rectangle; 0 if the point is contained in this rectangle
        if (node.rect.distanceSquaredTo(p) <= min) {

            double d = node.p.distanceSquaredTo(p);
            if (d < min) {
                min = d;
                currentNearest = node.p;
            }
            if (node.left != null && node.left.rect.contains(p)) {
                findNearest(p, node.left);
                findNearest(p, node.right);
            } else if (node.right != null && node.right.rect.contains(p)) {
                findNearest(p, node.right);
                findNearest(p, node.left);
            } else {
                double toLeft = node.left != null ? node.left.rect.distanceSquaredTo(p) : Double.POSITIVE_INFINITY;
                double toRight = node.right != null ? node.right.rect.distanceSquaredTo(p) : Double.POSITIVE_INFINITY;
                if (toLeft < toRight) {
                    findNearest(p, node.left);
                    findNearest(p, node.right);
                } else {
                    findNearest(p, node.right);
                    findNearest(p, node.left);
                }
            }
        }

    }

    public static void main(String[] args) {
        KdTree kd;
        kd = new KdTree();
        kd.insert(new Point2D(0.7, 0.2));
        kd.insert(new Point2D(0.5, 0.4));
        kd.insert(new Point2D(0.2, 0.3));
        kd.insert(new Point2D(0.4, 0.7));
        kd.insert(new Point2D(0.9, 0.6));
        kd.draw();
    }
}
