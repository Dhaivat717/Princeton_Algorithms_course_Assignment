public class Percolation {
    private static int[][] grid;
    private int gridSize;
    private unionFindPercolates U;
    private int openSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        U = new unionFindPercolates(n); // You can use princeton's WeightedQuickUnionUF(n * n + 2) package instead
        grid = new int[n][n];
        gridSize = n;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateState(row, col);
        if (isOpen(row, col) == false) {
            ++openSites;
            // connects with virtual top
            if (row == 0) {
                U.union(row * gridSize + col, gridSize * gridSize);
            }
            // connects with virtual bottom
            if (row == gridSize - 1) {
                U.union(row * gridSize + col, gridSize * gridSize + 1);
            }
            grid[row][col] = 1;
            // checks bottom row
            if (row > 0 && grid[row - 1][col] == 1) {
                int p = ((row - 1) * gridSize) + col;
                int q = row * gridSize + col;
                U.union(p, q);
            }
            // checks top row
            if (row < gridSize - 1 && grid[row + 1][col] == 1) {
                int p = ((row + 1) * gridSize) + col;
                int q = row * gridSize + col;
                U.union(p, q);
            }
            // checks right column
            if (col < gridSize - 1 && grid[row][col + 1] == 1) {
                int p = (row * gridSize) + col + 1;
                int q = row * gridSize + col;
                U.union(p, q);
            }
            // checks left column
            if (col > gridSize - 1 && grid[row][col - 1] == 1) {
                int p = (row - 1 * gridSize) + col - 1;
                int q = row * gridSize + col;
                U.union(p, q);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateState(row, col);
        if (grid[row][col] == 1) {
            return true;
        } else {
            return false;
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateState(row, col);
        return U.root(gridSize * row + col) == U.root(gridSize * gridSize); // use U.connected(gridSize * row + col,gridSize * gridSize) if you are using WeightedQuickUnionUF package
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return U.root(gridSize * gridSize) == U.root(gridSize * gridSize + 1); // use U.connected(gridSize * gridSize,gridSize * gridSize + 1) if you are using WeightedQuickUnionUF package
    }

    private void validateState(int row, int col) {
        boolean flag = false;
        if (row >= 0 && col >= 0 && row < gridSize && col < gridSize) {
            flag = true;
        }
        if (flag == false) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    // // test client (optional)   //test client if you want to experiment
    // public static void main(String[] args) {
    // p.open(0, 0);
    // p.open(1, 1);
    // p.open(1, 2);
    // p.open(2, 0);
    // p.open(1, 0);
    // p.open(3, 0);
    // for (int i = 0; i < 4; i++) {
    // for (int j = 0; j < 4; j++) {
    // System.out.print(grid[i][j]);
    // }
    // System.out.println(p.percolates());
    // }

    // }
}
