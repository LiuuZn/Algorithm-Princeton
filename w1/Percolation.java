/* *****************************************************************************
 *  Name: Xin Liu
 *  Date: 6.5.2019
 *  Description: W1 assignment
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private boolean[] mat;
    private WeightedQuickUnionUF block;
    private int len;
    private int numOpen = 0;
    private int rootIdentifier;


    public Percolation(int n)
    {
        if (n < 1)
        {
            throw new IllegalArgumentException("The input must be larger than 0");
        }
        else {
            len = n;
            block = new WeightedQuickUnionUF(len*len+1);
            mat = new boolean[len*len+1];
        }
    }

    private void validate(int row, int col)
    {
        if (row <= 0 || col <= 0 || row > len || col > len)
        {
            throw new IllegalArgumentException();
        }
    }

    private int calculateIndex(int row, int col)
    {
        return (row - 1) * len + col;
    }

    public void open(int row, int col)
    {
        validate(row, col);

        if (!isOpen(row, col))
        {
            numOpen += 1;
        }

        int curIdx = calculateIndex(row, col);

        mat[curIdx] = true;

        if (row == 1)
        {
           block.union(0, curIdx);
        } else if (row > 1 && isOpen(row-1, col))
        {
            block.union(calculateIndex(row-1, col), curIdx);
        }

        if (row < len && isOpen(row+1, col))
        {
            block.union(calculateIndex(row+1, col), curIdx);
        }

        if (col > 1 && isOpen(row, col-1))
        {
            block.union(calculateIndex(row, col-1), curIdx);
        }

        if (col < len && isOpen(row, col+1))
        {
            block.union(calculateIndex(row, col+1), curIdx);
        }

        rootIdentifier = block.find(0);



    }

    public boolean isOpen(int row, int col)
    {
        validate(row, col);
        int curIdx = calculateIndex(row, col);
        return mat[curIdx];

    }

    public boolean isFull(int row, int col)
    {
        validate(row, col);
        int curIdx = (row-1)* len +col;
        return block.find(curIdx) == rootIdentifier;
    }

    public int numberOfOpenSites()
    {
        return numOpen;
    }

    public boolean percolates()
    {

        for (int i = 0; i < len; i++)
        {
            if (mat[len*len - i])
            {
                if (block.find(len*len - i) == rootIdentifier)
                {
                    return true;
                }
            }

        }

        return false;

        // return isPercolated;
    }

    public static void main(String[] args) {
        // Percolation perc = new Percolation(8);
        // perc.open(1, 6);
        // perc.open(2, 6);
        // perc.open(3, 6);
        // perc.open(4, 6);
        // perc.open(5, 6);
        // perc.open(5, 5);
        // perc.open(4, 4);
        // System.out.println(perc.isFull(5,5));
        // perc.open(5, 4);
        // System.out.println(perc.isFull(4,4));

        // System.out.println(perc.percolates());
    }
}
