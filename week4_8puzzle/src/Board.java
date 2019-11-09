import java.util.ArrayList;
import java.util.Arrays;

/******************************************************************************
 *  Name: Xinyu
 *  Date: 10/03/2019
 *  Description:
 *****************************************************************************/

public class Board {
    private final int n;
    private final int[] plainBoard;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        assert n >= 2;
        plainBoard = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                plainBoard[i * n + j] = tiles[i][j];
            }
        }
    }

    private Board(int[] plainBoard) {
        this.plainBoard = plainBoard.clone();
        this.n = (int) Math.sqrt(plainBoard.length + 1);
    }


    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.n + "\n");
        int wrap = 0;
        for (int i = 0; i < n * n; i++) {
            int blockNum = n * n;
            int charLen = String.valueOf(blockNum).length() + 1;
            s.append(String.format("%" + charLen + "d", plainBoard[i]));
            // s.append(" " + plainBoard[i]);
            if (++wrap == n) {
                s.append("\n");
                wrap = 0;
            }
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < plainBoard.length - 1; i++) {
            if (plainBoard[i] != i + 1) count++;
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < plainBoard.length; i++) {
            if (plainBoard[i] == 0) {
                continue;
            }
            sum += Math.abs(i / n - (plainBoard[i] - 1) / n)
                    + Math.abs(i % n - (plainBoard[i] - 1) % n);
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < plainBoard.length - 1; i++) {
            if (plainBoard[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        return Arrays.equals(plainBoard, ((Board) y).plainBoard);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>(4);
        int blankIndex = 0;
        for (int i = 0; i < plainBoard.length; i++) {
            if (plainBoard[i] == 0) blankIndex = i;
        }

        int blankX = blankIndex / n;
        int blankY = blankIndex % n;

        if (blankY > 0) {
            neighbors.add(new Board(cloneSwap(plainBoard, blankIndex, blankIndex - 1)));
        }
        if (blankY < n - 1) {
            neighbors.add(new Board(cloneSwap(plainBoard, blankIndex, blankIndex + 1)));
        }
        if (blankX > 0) {
            neighbors.add(new Board(cloneSwap(plainBoard, blankIndex, blankIndex - n)));
        }
        if (blankX < n - 1) {
            neighbors.add(new Board(cloneSwap(plainBoard, blankIndex, blankIndex + n)));
        }

        return neighbors;
    }

    private int[] cloneSwap(int[] board, int i, int j) {
        int[] tl = board.clone();
        int temp = tl[i];
        tl[i] = tl[j];
        tl[j] = temp;
        return tl;
    }


    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        for (int i = 0; i < plainBoard.length - 1; i++) {
            if (plainBoard[i] != 0 && plainBoard[i + 1] != 0 && (i + 1) / n == i / n) {
                return new Board(cloneSwap(plainBoard, i, i + 1));
            }
        }
        return null;
    }


}
