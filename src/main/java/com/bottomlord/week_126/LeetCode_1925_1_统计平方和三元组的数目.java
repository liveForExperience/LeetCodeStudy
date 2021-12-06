package com.bottomlord.week_126;

/**
 * @author chen yue
 * @date 2021-12-06 09:07:48
 */
public class LeetCode_1925_1_统计平方和三元组的数目 {
    public int countTriples(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (Math.pow(i, 2) + Math.pow(j, 2) == Math.pow(k, 2)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
