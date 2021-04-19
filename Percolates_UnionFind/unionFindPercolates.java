public class unionFindPercolates {
    private int[] id;
    private int[] sz;

    public unionFindPercolates(int N) {
        id = new int[(N) * (N) + 2];
        sz = new int[(N) * (N) + 2];
        for (int i = 0; i < N * N + 2; ++i) {
            id[i] = i;
            sz[i] = 0;
        }
    }

    public int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] > sz[j]) {
            id[j] = i;
            sz[i] += sz[j];
        } else {
            id[i] = j;
            sz[j] += sz[i];
        }
    }
}
