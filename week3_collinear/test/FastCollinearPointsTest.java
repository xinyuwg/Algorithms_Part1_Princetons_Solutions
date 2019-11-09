import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastCollinearPointsTest {

    @Test
    void shouldFindHorizontalLineSegment() {
        // given
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 0), new Point(3, 0), new Point(1, 1), new Point(2, 0)};

        // when
        FastCollinearPoints cut = new FastCollinearPoints(points);

        // then
        assertEquals(1, cut.numberOfSegments());
        assertEquals("(0, 0) -> (3, 0)", cut.segments()[0].toString());
    }

    @Test
    void shouldFindVerticalLineSegment() {
        // given
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 1), new Point(0, 1), new Point(0, 2), new Point(0, 3)};

        // when
        FastCollinearPoints cut = new FastCollinearPoints(points);

        // then
        assertEquals(1, cut.numberOfSegments());
        assertEquals("(0, 0) -> (0, 3)", cut.segments()[0].toString());
    }

    @Test
    void shouldFindLineSegment1() {
        // given
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 1), new Point(0, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5)};

        // when
        FastCollinearPoints cut = new FastCollinearPoints(points);

        // then
        assertEquals(1, cut.numberOfSegments());
        assertEquals("(0, 0) -> (5, 5)", cut.segments()[0].toString());
    }

    @Test
    void shouldFindLineSegment2() {
        // given
        Point[] points = new Point[]{new Point(0, 0), new Point(1, 2), new Point(0, 1), new Point(2, 4), new Point(3, 6)};

        // when
        FastCollinearPoints cut = new FastCollinearPoints(points);

        // then
        assertEquals(1, cut.numberOfSegments());
        assertEquals("(0, 0) -> (3, 6)", cut.segments()[0].toString());
    }
}