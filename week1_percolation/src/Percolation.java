/* *****************************************************************************
 *  Name: Xinyu Wang
 *  Date: 09/13/2019
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufWashBackSolver;
    private boolean[] status;
    private int openSiteNum = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n is illegal.");
        this.n = n;
        status = new boolean[(n + 2) * (n + 2)];
        uf = new WeightedQuickUnionUF((n + 2) * (n + 2));
        ufWashBackSolver = new WeightedQuickUnionUF((n + 1) * (n + 2));
        for (int i = 0; i < n + 2; i++) {
            status[i] = true;
            status[(n + 1) * (n + 2) + i] = true;
        }
        for (int i = 0; i < n + 1; i++) {
            uf.union(i, i + 1);
            ufWashBackSolver.union(i, i + 1);
            uf.union((n + 1) * (n + 2) + i, (n + 1) * (n + 2) + i + 1);
        }
    }

    // opens the site (row,col) if it is not open already
    public void open(int row, int col) {
        checkIndex(row, col);
        int index = row * (n + 2) + col;


        if (!isOpen(row, col)) {
            status[index] = true;
            openSiteNum++;
        }
        if (row == 1 || isOpen(row - 1, col)) {
            uf.union(index, (row - 1) * (n + 2) + col);
            ufWashBackSolver.union(index, (row - 1) * (n + 2) + col);
        }
        if (row == n) {
            uf.union(index, (row + 1) * (n + 2) + col);
        }
        if (row < n && isOpen(row + 1, col)) {
            uf.union(index, (row + 1) * (n + 2) + col);
            ufWashBackSolver.union(index, (row + 1) * (n + 2) + col);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(index, row * (n + 2) + col - 1);
            ufWashBackSolver.union(index, row * (n + 2) + col - 1);
        }
        if (col < n && isOpen(row, col + 1)) {
            uf.union(index, row * (n + 2) + col + 1);
            ufWashBackSolver.union(index, row * (n + 2) + col + 1);
        }
    }

    // is the site (row,col) open?
    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        return status[row * (n + 2) + col];
    }

    // is the site (row,col) full?
    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        // if() /
        return ufWashBackSolver.connected(0, row * (n + 2) + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteNum;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, (n + 2) * (n + 2) - 1);
    }

    private void checkIndex(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("row or col is illegal.");
    }

    // test client
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 2);
        StdOut.println(p.isOpen(1, 1));
        StdOut.println(p.percolates());
    }
}
