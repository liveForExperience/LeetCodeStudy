package com.bottomlord.week_140;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-15 21:02:35
 */
public class LeetCode_2200_1_找出数组中的所有K近邻下标 {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == key) {
                indexes.add(i);
            }
        }

        boolean[] arr = new boolean[nums.length];
        for (Integer index : indexes) {
            int head = Math.max(0, index - k),
                tail = Math.min(nums.length - 1, index + k);

            for (int i = head; i <= tail; i++) {
                arr[i] = true;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}
