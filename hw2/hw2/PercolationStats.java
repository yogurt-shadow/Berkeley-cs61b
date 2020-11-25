package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private double[] threshold;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("experiment times and grid size should be positive");
        }
        this.N = N;
        this.T = T;
        threshold = new double[T];
        for (int i = 0; i < T; i++) {
            int thistime = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    thistime += 1;
                }
            }
            threshold[i] = (double) thistime / (double) (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(threshold);
    }

    public double stddev() {
        return StdStats.stddev(threshold);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
/**
 public static void main(String[] args) {
 */
    /**
     PercolationFactory pf = new PercolationFactory();
     Stopwatch timer1 = new Stopwatch();
     PercolationStats ps1 = new PercolationStats(20, 400, pf);
     double time1 = timer1.elapsedTime();
     System.out.printf("(%.2f seconds)\n", time1);

     Stopwatch timer2 = new Stopwatch();
     PercolationStats ps2 = new PercolationStats(40, 400, pf);
     double time2 = timer2.elapsedTime();
     System.out.printf("(%.2f seconds)\n", time2);


     Stopwatch timer3 = new Stopwatch();
     PercolationStats ps3 = new PercolationStats(20, 800, pf);
     double time3 = timer3.elapsedTime();
     System.out.printf("(%.2f seconds)\n", time3);
     */
    /**
     }
     */
}
