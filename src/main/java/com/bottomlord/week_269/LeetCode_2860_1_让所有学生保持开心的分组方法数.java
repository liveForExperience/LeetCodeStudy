package com.bottomlord.week_269;

import java.util.Collections;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-09-04 16:06:52
 */
public class LeetCode_2860_1_让所有学生保持开心的分组方法数 {
    public int countWays(List<Integer> nums) {
        int ans = 0, n = nums.size();
        Collections.sort(nums);
        for (int i = 0; i < n - 1; i++) {;
            if (nums.get(i) < i + 1 &&  nums.get(i + 1) > i + 1) {
                ans++;
            }
        }
        int last = nums.get(n - 1);
        return last < n ? ans + 1 : ans;
    }
}
