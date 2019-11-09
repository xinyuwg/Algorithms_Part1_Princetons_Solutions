import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private static final int[][] EXAMPLE_BLOCK = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
    private static final int[][] GOAL = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    void testDimension() {
        assertEquals(3, new Board(EXAMPLE_BLOCK).dimension());
    }

    @Test
    void testHammingValue() {
        assertEquals(5, new Board(EXAMPLE_BLOCK).hamming());
    }

    @Test
    void testManhattan() {
        assertEquals(10, new Board(EXAMPLE_BLOCK).manhattan());
    }

    @Test
    void testIsGoal() {
        assertTrue(new Board(GOAL).isGoal());
        assertFalse(new Board(EXAMPLE_BLOCK).isGoal());
    }

    @Test
    void shouldGiveAllNeighbors() {
        int numberOfNeighbors = 0;
        for (Board neighbor : new Board(EXAMPLE_BLOCK).neighbors()) {
            assertNotNull(neighbor);
            numberOfNeighbors++;
        }
        assertEquals(4, numberOfNeighbors);


        numberOfNeighbors = 0;
        for (Board neighbor : new Board(GOAL).neighbors()) {
            assertNotNull(neighbor);
            numberOfNeighbors++;
        }
        assertEquals(2, numberOfNeighbors);
    }

    @Test
    void shouldGiveTwin() {
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        assertEquals(new Board(new int[][]{{2, 1, 3}, {4, 5, 6}, {7, 8, 0}}), board.twin());
    }

    @Test
    void shouldGiveAlternativeTwin1() {
        Board board = new Board(new int[][]{{0, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertEquals(new Board(new int[][]{{0, 3, 2}, {4, 5, 6}, {7, 8, 9}}), board.twin());
    }

    @Test
    void shouldGiveAlternativeTwin2() {
        Board board = new Board(new int[][]{{1, 0, 3}, {4, 5, 6}, {7, 8, 9}});
        assertEquals(new Board(new int[][]{{1, 0, 3}, {5, 4, 6}, {7, 8, 9}}), board.twin());
    }

}