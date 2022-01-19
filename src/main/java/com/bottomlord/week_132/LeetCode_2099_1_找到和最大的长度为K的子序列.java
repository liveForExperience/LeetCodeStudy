package com.bottomlord.week_132;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-01-19 10:39:38
 */
public class LeetCode_2099_1_找到和最大的长度为K的子序列 {
    public int[] maxSubsequence(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        for (int i = copy.length - 1; i >= copy.length - k; i--) {
            int num = copy[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] ans = new int[k];
        int index = 0;
        for (int num : nums) {
            if (index == k) {
                break;
            }

            if (!map.containsKey(num)) {
                continue;
            }

            ans[index++] = num;

            map.put(num, map.get(num) - 1);

            if (map.get(num) == 0) {
                map.remove(num);
            }
        }

        return ans;
    }
}
