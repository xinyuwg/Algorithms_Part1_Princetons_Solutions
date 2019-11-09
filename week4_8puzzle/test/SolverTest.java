import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    private static final Board NON_SOLVABLE_BOARD = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {8, 7, 0}});
    private static final Board SOLVABLE_BOARD = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});
    private static final Board SOLVED_BOARD = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});

    @Test
    void shouldFindFastestSolution() {
        Solver solver = new Solver(SOLVABLE_BOARD);
        assertEquals(4, solver.moves());
    }

    @Test
    void shouldGiveAllStepsFromStartToSolution() {
        Solver solver = new Solver(SOLVABLE_BOARD);

        int currentStep = 0;
        for (Board board : solver.solution()) {
            switch (currentStep) {
                case 0:
                    assertEquals(board, SOLVABLE_BOARD);
                    break;
                case 1:
                    assertEquals(board, new Board(new int[][]{{1, 0, 3}, {4, 2, 5}, {7, 8, 6}}));
                    break;
                case 2:
                    assertEquals(board, new Board(new int[][]{{1, 2, 3}, {4, 0, 5}, {7, 8, 6}}));
                    break;
                case 3:
                    assertEquals(board, new Board(new int[][]{{1, 2, 3}, {4, 5, 0}, {7, 8, 6}}));
                    break;
                case 4:
                    assertEquals(board, SOLVED_BOARD);
                    break;
                default:
                    fail("Too many steps given");

            }
            currentStep++;
        }

        assertEquals(5, currentStep);
    }

    @Test
    void shouldGiveNoMovesForSolvedBoard() {
        assertEquals(0, new Solver(SOLVED_BOARD).moves());
    }

    @Test
    void shouldRecognizeIfBoardIsSolvable() {
        assertTrue(new Solver(SOLVABLE_BOARD).isSolvable());
        assertFalse(new Solver(NON_SOLVABLE_BOARD).isSolvable());
    }



}