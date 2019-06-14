/* *****************************************************************************
 *  Name: Xin Liu
 *  Date: 6-11-2019
 *  Description: w1 assignment
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private double[] result;
    private int n;
    private int trials;
    private double mean;
    private double std;

    public PercolationStats(int n, int trials)
    {
        if (n < 1 || trials < 1)
        {
            throw new IllegalArgumentException("The input must be larger than 0");
        }
        else
        {
            this.n = n;
            this.trials = trials;
            result = new double[trials];

            for (int i = 0; i < trials; i++)
            {
                Percolation model = new Percolation(n);
                do
                {
                    int rand1;
                    int rand2;

                    rand1 = StdRandom.uniform(1, n+1);
                    rand2 = StdRandom.uniform(1, n+1);

                    model.open(rand1, rand2);

                } while (!model.percolates());

                result[i] = model.numberOfOpenSites();

            }

            mean = mean();
            std = stddev();

        }
    }

    public double mean()
    {
        return StdStats.mean(result)/(n*n);
    }

    public double stddev()
    {
        return StdStats.stddev(result)/(n*n);
    }

    public double confidenceLo()
    {
        return mean - calculateGap();
    }

    public double confidenceHi()
    {
        return mean + calculateGap();
    }

    private double calculateGap()
    {
        double gap = 1.96 * this.std / Math.sqrt(this.trials);
        return gap;
    }

    public static void main(String[] args)
    {
        int numTest = Integer.parseInt(args[0]);
        int timeTest = Integer.parseInt(args[1]);
        PercolationStats mo = new PercolationStats(numTest, timeTest);
        // System.out.printf("mean\t=%f\n", mo.mean);
        // System.out.printf("stddev\t=%f\n", mo.std);
        // System.out.printf("95%% confidence interval\t= [%f, %f]\n", mo.confidenceLo(), mo.confidenceHi());


    }
}
