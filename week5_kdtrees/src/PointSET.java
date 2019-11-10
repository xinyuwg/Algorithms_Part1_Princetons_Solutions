import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;
import java.util.List;


public class PointSET {
    private SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new SET<>();
    }

    //    is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number if points in the set
    public int size() {
        return points.size();
    }

    //    add the points to the set
    public void insert(Point2D point) {
        checkInputValid(point);
        points.add(point);
    }

    //    does the set contain point p?
    public boolean contains(Point2D point) {
        checkInputValid(point);
        return points.contains(point);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    //    all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> insidePoints = new ArrayList<>();
        for (Point2D point : points) {
            if (rect.contains(point)) insidePoints.add(point);
        }
        return insidePoints;
    }

    // the nearest neighbor in the set to point P; null if the set is empty
    public Point2D nearest(Point2D point) {
        Point2D nearestPoint = null;
        for (Point2D point2D : points) {
            if (nearestPoint == null || point.distanceTo(point2D) < point.distanceTo(nearestPoint))
                nearestPoint = point2D;
        }
        return nearestPoint;
    }

    private static void checkInputValid(Object o) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }


}
