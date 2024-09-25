package com.bottomlord.week_071;

/**
 * @author ChenYue
 * @date 2020/11/16 8:26
 */
public class LeetCode_335_1_路径交叉 {
    public boolean isSelfCrossing(int[] x) {
        if (x.length <= 3) {
            return false;
        }

        for (int i = 3; i < x.length; i++) {
            if (x[i] >= x[i - 2] && x[i - 1] <= x[i - 3]) {
                return true;
            }

            if (i >= 4 && x[i - 1] == x[i - 3] && x[i] + x[i - 4] >= x[i - 2]) {
                return true;
            }

            if (i >= 5 && x[i] + x[i - 4] >= x[i - 2] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 4] < x[i - 2] && x[i] < x[3]) {
                return true;
            }
        }

        return false;
    }
}
