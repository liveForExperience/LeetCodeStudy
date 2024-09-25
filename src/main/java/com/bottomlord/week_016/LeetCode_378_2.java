package com.bottomlord.week_016;

public class LeetCode_378_2 {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[row - 1].length, head = matrix[0][0], tail = matrix[row - 1][col - 1];
        while (head < tail) {
            int mid = head + (tail - head) / 2 , count = 0, r = row - 1, c = 0;

            while (r >= 0 && c < col) {
                if (matrix[r][c] <= mid) {
                    count += r + 1;
                    c++;
                } else {
                    r--;
                }
            }

            if (count < k) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return tail;
    }
}