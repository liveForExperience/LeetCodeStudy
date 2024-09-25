package com.bottomlord.week_108;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/8/3 8:38
 */
public class LeetCode_1426_1_数元素 {
    public int countElements(int[] arr) {
        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }

        int ans = 0;
        for (int num : arr) {
            if (set.contains(num + 1)) {
                ans++;
            }
        }

        return ans;
    }
}
