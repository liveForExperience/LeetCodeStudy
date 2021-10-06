package com.bottomlord.week_117;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-10-06 09:06:07
 */
public class LeetCode_414_1_第三大的数 {
    public int thirdMax(int[] nums) {
        int a = nums[0];
        Integer b = null, c = null;
        Set<Integer> set = new HashSet<>();
        set.add(a);
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (!set.add(num)) {
                continue;
            }

            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (b == null) {
                b = num;
            } else if (num > b) {
                c = b;
                b = num;
            } else if (c == null) {
                c = num;
            } else if (num > c) {
                c = num;
            }
        }

        return c == null ? a : c;
    }
}
