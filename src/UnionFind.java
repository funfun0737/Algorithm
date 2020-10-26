public class UnionFind {
    int[] p;
    int[] size;
    int[] rank;
    int count;

    UnionFind(int n) {
        this.p = new int[n];
        this.size = new int[n];
        this.rank = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) {
            p[i] = i;
            size[i] = 1;
        }
    }

    void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (pi != pj) {
            p[pi] = pj;
            count--;
        }
    }

    void unionBySize(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (pi == pj) {
            return;
        }
        if (size[pi] > size[pj]) {
            p[pj] = pi;
            size[pi] += size[pj];
        } else {
            p[pi] = pj;
            size[pj] += size[pi];
        }
        count--;
    }

    void unionByRank(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (pi == pj) {
            return;
        }
        if (rank[pi] > rank[pj]) {
            p[pj] = pi;
        } else {
            p[pi] = pj;
            if (rank[pj] == rank[pi]) {
                rank[pj]++;
            }
        }
        count--;
    }

    int find(int i) {
        while (i != p[i]) {
            p[i] = p[p[i]]; // path compression. set grandparent of i to be it parent
            i = p[i];
        }

        return i;
    }
}
