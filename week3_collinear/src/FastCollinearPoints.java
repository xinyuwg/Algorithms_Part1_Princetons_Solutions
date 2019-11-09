/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();
    private FoundSegmentMap foundSegments = new FoundSegmentMap();

    private class PointKeyValue {
        private double slope;
        private ArrayList<Point> endPoints;

        public PointKeyValue(double slope, ArrayList<Point> endPoints) {
            this.slope = slope;
            this.endPoints = endPoints;
        }
    }

    private class FoundSegmentMap {
        private ArrayList<PointKeyValue> foundSegmentMap = new ArrayList<>();

        public void put(PointKeyValue pkv) {
            foundSegmentMap.add(pkv);
        }

        public ArrayList<Point> get(double slope) {
            for (PointKeyValue keyValue : foundSegmentMap) {
                if (keyValue.slope == slope) {
                    return keyValue.endPoints;
                }
            }
            return null;
        }


    }


    public FastCollinearPoints(Point[] points) {
        checkInputValid(points);
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        checkDuplicated(pointsCopy);
        for (int i = 0; i < pointsCopy.length - 1; i++) {
            Point origin = pointsCopy[i];
            Point[] remanents = Arrays.copyOfRange(pointsCopy, i + 1, points.length);
            Arrays.sort(remanents, origin.slopeOrder());
            int start = 0;
            int end = 1;
            int count = 1;
            double previousSlope = origin.slopeTo(remanents[start]);
            while (end < remanents.length) {
                if (origin.slopeTo(remanents[end]) == previousSlope) {
                    count++;
                }
                else {
                    if (count >= 3) addSegmentIfNew(previousSlope, origin, remanents[end - 1]);
                    count = 1;
                    start = end;
                    previousSlope = origin.slopeTo(remanents[start]);
                }
                end++;
            }
            if (count >= 3) addSegmentIfNew(previousSlope, origin, remanents[end - 1]);


        }


    }

    private void addSegmentIfNew(double slope, Point origin, Point endPoint) {
        ArrayList<Point> endPoints = foundSegments.get(slope);
        if (endPoints == null) {
            endPoints = new ArrayList<Point>();
            endPoints.add(endPoint);
            foundSegments.put(new PointKeyValue(slope, endPoints));
        }
        else {
            for (Point point : endPoints) {
                if (endPoint.compareTo(point) == 0) return;
            }
            endPoints.add(endPoint);
        }
        segments.add(new LineSegment(origin, endPoint));


    }


    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[numberOfSegments()]);
    }

    private void checkDuplicated(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++)
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Input includes duplicate entries");
    }

    private void checkInputValid(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Input include null entries");
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
