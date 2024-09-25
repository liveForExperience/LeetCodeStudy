package com.bottomlord.week_081;

public class LeetCode_1631_2 {
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length, col = heights[0].length;

        int[][] arrs = new int[row * (col - 1) + col * (row - 1)][3];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int id = i * col + j;
                if (i > 0) {
                    arrs[index++] = new int[]{id - col, id, Math.abs(heights[i][j] - heights[i - 1][j])};
                }

                if (j > 0) {
                    arrs[index++] = new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])};
                }
            }
        }

        quickSort(arrs);
        UF uf = new UF(row * col);

        for (int[] arr : arrs) {
            int x = arr[0], y = arr[1], v = arr[2];
            uf.union(x, y);
            if (uf.isConnected(0, row * col - 1)) {
                return v;
            }
        }

        return 0;
    }

    private void quickSort(int[][] arr) {
        doQuickSort(arr, 0, arr.length - 1);
    }

    private void doQuickSort(int[][] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int partition = partition(arr, start, end);

        doQuickSort(arr, start, partition - 1);
        doQuickSort(arr, partition + 1, end);
    }

    private int partition(int[][] arr, int start, int end) {
        int[] pivot = arr[start];

        while (start < end) {
            while (start < end && arr[end][2] >= pivot[2]) {
                end--;
            }
            arr[start] = arr[end];

            while (start < end && arr[start][2] <= pivot[2]) {
                start++;
            }
            arr[end] = arr[start];
        }

        arr[start] = pivot;
        return start;
    }

    static class UF {
        private int[] parents;

        public UF(int n) {
            this.parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            parents[find(x)] = parents[y];
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
}
