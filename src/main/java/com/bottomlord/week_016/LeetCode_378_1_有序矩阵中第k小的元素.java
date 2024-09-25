package com.bottomlord.week_016;

public class LeetCode_378_1_有序矩阵中第k小的元素 {
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[row - 1].length, head = matrix[0][0], tail = matrix[row - 1][col - 1];
        while (head < tail) {
            int mid = head + (tail - head) / 2 , count = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] <= mid) {
                        count++;
                    } else {
                        break;
                    }
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
