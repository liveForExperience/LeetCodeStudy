package com.bottomlord.contest_20220515;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-05-15 10:24:14
 */
public class Contest_2_1_不含特殊楼层的最大连续楼层数 {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Set<Integer> set = new HashSet<>();
        for (int num : special) {
            set.add(num);
        }

        int count = 0, max = count;
        for (int i = bottom; i <= top; i++) {
            if (set.contains(i)) {
                max = Math.max(max, count);
                count = 0;
            } else {
                count++;
            }
        }

        max = Math.max(max, count);

        return max;
    }
}
