import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFromFile {
    @Test
    void puzzle00() {
        executeGenericTest(0);
    }

    @Test
    void puzzle01() {
        executeGenericTest(1);
    }

    @Test
    void puzzle02() {
        executeGenericTest(2);
    }

    @Test
    void name() {
    }

    @Test
    void puzzle04() {
        executeGenericTest(4);
    }

    @Test
    void puzzle05() {
        executeGenericTest(5);
    }

    @Test
    void puzzle06() {
        executeGenericTest(6);
    }

    @Test
    void puzzle07() {
        executeGenericTest(7);
    }

    @Test
    void puzzle08() {
        executeGenericTest(8);
    }

    @Test
    void puzzle09() {
        executeGenericTest(9);
    }

    @Test
    void puzzle10() {
        executeGenericTest(10);
    }

    @Test
    void puzzle11() {
        executeGenericTest(11);
    }

    @Test
    void puzzle12() {
        executeGenericTest(12);
    }

    @Test
    void puzzle13() {
        executeGenericTest(13);
    }

    private void executeGenericTest(int puzzle) {
        assertEquals(puzzle, new Solver(getBlockFromFile("puzzle" + ((puzzle < 10) ? "0" : "") + puzzle + ".txt")).moves());
    }

    private Board getBlockFromFile(String fileName) {
        In in = new In("test_input/" + fileName);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        return new Board(blocks);
    }
}
