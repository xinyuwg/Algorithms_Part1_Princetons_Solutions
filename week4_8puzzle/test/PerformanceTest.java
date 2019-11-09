import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PerformanceTest {

    @Test
    void puzzle20() {
        executeGenericTest(20);
    }

    @Test
    void puzzle21() {
        executeGenericTest(21);
    }

    @Test
    void puzzle22() {
        executeGenericTest(22);
    }

    @Test
    void puzzle23() {
        executeGenericTest(23);
    }

    @Test
    void puzzle24() {
        executeGenericTest(24);
    }

    @Test
    void puzzle25() {
        executeGenericTest(25);
    }

    @Test
    void puzzle26() {
        executeGenericTest(26);
    }

    @Test
    void puzzle27() {
        executeGenericTest(27);
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
