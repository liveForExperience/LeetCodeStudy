package com.bottomlord.week_004;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/8/2 12:52
 */
public class LeetCode_202_1_快乐数 {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            int sum = 0;
            while (n > 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }

            if (sum == 1) {
                return true;
            }

            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            n = sum;
        }
    }
}
