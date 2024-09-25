package com.bottomlord.week_106;

/**
 * @author ChenYue
 * @date 2021/7/23 8:12
 */
public class LeetCode_1893_1_检查是否区域内所有整数都被覆盖 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        boolean init = false;
        for (int[] range : ranges) {
            if (range[1] < left || range[0] > right) {
                continue;
            }

            if (!init) {
                l = range[0];
                r = range[1];
                init = true;
                continue;
            }

            if (range[0] <= l && range[1] >= r) {
                l = range[0];
                r = range[1];
                continue;
            }

            if (range[0] >= l && range[0] <= r + 1 && range[1] > r) {
                r = range[1];
                continue;
            }

            if (range[1] <= r && range[1] >= l - 1 && range[0] < l) {
                l = range[0];
            }
        }

        return l <= left && r >= right;
    }
}
