package com.bottomlord.week_113;

/**
 * @author chen yue
 * @date 2021-09-10 08:31:47
 */
public class LeetCode_1894_1_找到需要补充粉笔的学生编号 {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        int[] sums = new int[len];
        sums[0] = chalk[0];
        if (sums[0] > k) {
            return 0;
        }

        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + chalk[i];

            if (sums[i] > k) {
                return i;
            }
        }

        k %= sums[len - 1];

        for (int i = 0; i < len; i++) {
            if (sums[i] > k) {
                return i;
            }
        }

        return -1;
    }
}
