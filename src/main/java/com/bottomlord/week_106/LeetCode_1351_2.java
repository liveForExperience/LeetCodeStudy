package com.bottomlord.week_106;

/**
 * @author ChenYue
 * @date 2021/7/20 9:02
 */
public class LeetCode_1351_2 {
    public int countNegatives(int[][] grid) {
        int col = grid[0].length, count = 0;
        for (int[] row : grid) {
            if (row[0] < 0) {
                count += col;
                continue;
            }

            if (row[col - 1] >= 0) {
                continue;
            }

            int head = 0, tail = col - 1;
            while (head <= tail) {
                int mid = head + (tail - head) / 2;
                if (row[mid] < 0) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            }

            count += col - head;
        }

        return count;
    }
}
