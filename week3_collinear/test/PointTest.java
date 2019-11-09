import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private static final double VALID_DELTA = 0;

    @Test
     void shouldReturnNegativeInfinityWhenComparingIdenticalPoints() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2), VALID_DELTA);
    }

    @Test
     void shouldReturnPositiveInfinityWhenComparingVerticalPoints() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2), VALID_DELTA);
    }

    @Test
     void shouldReturnPositiveZeroWhenComparingHorizontalPoints() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 1);
        assertEquals(+0, p1.slopeTo(p2), VALID_DELTA);
    }

    @Test
     void shouldCalculateCorrectSlope() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        assertEquals(1.0, p1.slopeTo(p2), VALID_DELTA);
    }

    @Test
     void shouldReturnZeroWhenComparingIdenticalPoints() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        assertEquals(0, p1.compareTo(p2));
    }

    @Test
     void shouldReturnPositiveOneWhenComparingWithSmallerPoint() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(0, 0);
        assertEquals(+1, p1.compareTo(p2));
    }

    @Test
     void shouldReturnNegativeOneWhenComparingWithSmallerPoint() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        assertEquals(-1, p1.compareTo(p2));
    }

    @Test
     void comparatorShouldReturnZeroWhenComparingTwoPointsWithEqualSlopeRelativeToReferencePoint() {
        Point referencePoint = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);

        Comparator<Point> comparator = referencePoint.slopeOrder();
        assertEquals(0, comparator.compare(p1, p2));
    }

    @Test
     void comparatorShouldReturnPositiveOneWhenComparingPointWithBiggerSlopeAndPointWithSmallerSlopeRelativeToReferencePoint() {
        Point referencePoint = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 0);

        Comparator<Point> comparator = referencePoint.slopeOrder();
        assertEquals(1, comparator.compare(p1, p2));
    }

    @Test
     void comparatorShouldReturnNegativeOneWhenComparingPointWithSmallerSlopeAndPointWithBiggerSlopeRelativeToReferencePoint() {
        Point referencePoint = new Point(0, 0);
        Point p1 = new Point(1, 0);
        Point p2 = new Point(2, 2);

        Comparator<Point> comparator = referencePoint.slopeOrder();
        assertEquals(-1, comparator.compare(p1, p2));
    }
}