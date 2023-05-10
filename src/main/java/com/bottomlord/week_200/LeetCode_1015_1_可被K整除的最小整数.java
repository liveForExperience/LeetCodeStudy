package com.bottomlord.week_200;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-05-10 22:46:49
 */
public class LeetCode_1015_1_可被K整除的最小整数 {
    public int smallestRepunitDivByK(int k) {
        Set<Integer> set = new HashSet<>();
        int x = 1;
        for (int i = 1; ; i++) {
            if (x % k == 0) {
                return i;
            }

            x = (x % k) * 10 + 1;
            if (!set.add(x)) {
                return -1;
            }
        }
    }
}
