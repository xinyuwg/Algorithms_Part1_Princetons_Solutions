import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteCollinearPointsTest {


    @Test
    void shouldFindHorizontalLineSegment() {
        // given
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 1), new Point(1, 0), new Point(2, 0), new Point(3, 0)};

        // when
        BruteCollinearPoints cut = new BruteCollinearPoints(points);

        // then
        assertEquals(1, cut.numberOfSegments());
        assertEquals("(0, 0) -> (3, 0)", cut.segments()[0].toString());
    }

    @Test
    void shouldFindVerticalLineSegment() {
        // given
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 1), new Point(0, 1), new Point(0, 2), new Point(0, 3)};

        // when
        BruteCollinearPoints cut = new BruteCollinearPoints(points);

        // then
        assertEquals(1, cut.numberOfSegments());
        assertEquals("(0, 0) -> (0, 3)", cut.segments()[0].toString());
    }

    @Test
    void shouldFindLineSegment1() {
        // given
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 1), new Point(0, 1), new Point(2, 2), new Point(3, 3)};

        // when
        BruteCollinearPoints cut = new BruteCollinearPoints(points);

        // then
        assertEquals(1, cut.numberOfSegments());
        assertEquals("(0, 0) -> (3, 3)", cut.segments()[0].toString());
    }

    @Test
    void shouldFindLineSegment2() {
        // given
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 2), new Point(0, 1), new Point(2, 4), new Point(3, 6)};

        // when
        BruteCollinearPoints cut = new BruteCollinearPoints(points);

        // then
        assertEquals(1, cut.numberOfSegments());
        assertEquals("(0, 0) -> (3, 6)", cut.segments()[0].toString());
    }

}