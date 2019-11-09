/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int expNum;
    private final double mean;
    private final double stddev;


    public PercolationStats(int n, int t) {
        if (n < 1 || t < 1) throw new IllegalArgumentException();
        this.expNum = t;
        double[] expRes = new double[expNum];
        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                p.open(row, col);
            }
            expRes[i] = (double) p.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(expRes);
        stddev = StdStats.stddev(expRes);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(expNum);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(expNum);
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]), t = Integer.parseInt(args[1]);
        PercolationStats pstats = new PercolationStats(n, t);

        System.out.printf("mean                     = %f\n", pstats.mean());
        System.out.printf("stddev                   = %f\n", pstats.stddev());
        System.out.printf("95%s confidence interval  = [%f, %f]\n", "%", pstats.confidenceLo(),
                          pstats.confidenceHi());
    }
}
