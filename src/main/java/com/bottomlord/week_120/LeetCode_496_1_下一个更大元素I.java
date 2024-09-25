package com.bottomlord.week_120;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author chen yue
 * @date 2021-10-26 10:32:35
 */
public class LeetCode_496_1_下一个更大元素I {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> mapping = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];
            while (!stack.isEmpty() && num > stack.peek()) {
                stack.pop();
            }

            mapping.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = mapping.get(nums1[i]);
        }

        return ans;
    }
}
