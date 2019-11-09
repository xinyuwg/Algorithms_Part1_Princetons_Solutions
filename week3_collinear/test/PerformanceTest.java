import edu.princeton.cs.algs4.Stopwatch;
import org.junit.jupiter.api.Test;

 class PerformanceTest {

    @Test
     void NEquals1024() {
        Point[] points = getTestGrid(1024);

        Stopwatch stopwatch = new Stopwatch();
        FastCollinearPoints cut = new FastCollinearPoints(points);
        double elapsedTime = stopwatch.elapsedTime();

        System.out.println("Elapsed Time:\t" + elapsedTime);
        System.out.println("Found Segments:\t" + cut.numberOfSegments());
//        for(LineSegment segment : cut.segments()) {
//            System.out.println(segment);
//        }
    }

    private Point[] getTestGrid(int n) {
        Point[] points = new Point[n];
        for (int x = 0; x < n / 4; x++) {
            for (int y = 0; y < 4; y++) {
                points[x * 4 + y] = new Point(x, y);
            }
        }
        return points;
    }


}
