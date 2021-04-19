public class PercolationStats {
    private Percolation per;
    private double[] trialResults;
    private int noOfTrials;
    private static double meanF, stdF;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }
        noOfTrials = trials;
        trialResults = new double[trials];
        for (int i = 0; i < trials; ++i) {
            per = new Percolation(n);

            while (!(per.percolates())) {
                int row = (int) Math.floor(Math.random() * (n)); // you can use StdRandom.uniform(n) if you are using princeton package
                int col = (int) Math.floor(Math.random() * (n));
                per.open(row, col);
            }
            int openSquares = per.numberOfOpenSites();
            double ratio = (double) openSquares / (n * n);
            trialResults[i] = ratio;
        }
    }
    // You don't have to define private double calculateSD(double numArray[]) and private double calculateMean(double numArray[]) 
    // If you are using princeton package.

    private double calculateSD(double numArray[]) {
        double sum = 0.0, standardDeviation = 0.0;

        for (double num : numArray) {
            sum += num;
        }
        double mean = sum / noOfTrials;
        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return standardDeviation / (noOfTrials - 1);
    }

    private double calculateMean(double numArray[]) {
        double sum = 0.0;

        for (double num : numArray) {
            sum += num;
        }
        double mean = sum / noOfTrials;
        return mean;
    }

    // sample mean of percolation threshold
    public double mean() {
        meanF = calculateMean(trialResults);
        return meanF; // You can directly return StdStats.mean(trialResults); if you are using princeton package
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stdF = calculateSD(trialResults);
        return stdF; // You can directly return StdStats.stddev(trialResults); if you are using princeton package
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return meanF - (1.96 * Math.sqrt(stdF) / Math.sqrt(noOfTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return meanF + (1.96 * Math.sqrt(stdF) / Math.sqrt(noOfTrials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int tries = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(size, tries);
        System.out.println("mean                    = " + p.mean());
        System.out.println("stddev                  = " + p.stddev());
        String confidence = p.confidenceLo() + ", " + p.confidenceHi();
        System.out.println("95% confidence interval = " + confidence);

    }

}
