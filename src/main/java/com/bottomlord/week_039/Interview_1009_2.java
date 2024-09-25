package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/4 23:06
 */
public class Interview_1009_2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (search(row, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean search(int[] row, int target) {
        int l = 0, r = row.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (row[mid] == target) {
                return true;
            }

            if (row[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
