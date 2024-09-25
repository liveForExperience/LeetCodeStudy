package com.bottomlord.week_231;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-12-12 19:25:14
 */
public class LeetCode_2454_1_下一个更大元素IV {
    public int[] secondGreaterElement(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> nums[x]));
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            while (!queue.isEmpty() && nums[queue.peek()] < cur) {
                ans[queue.poll()] = cur;
            }

            while (!stack.isEmpty() && nums[stack.peek()] < cur) {
                queue.offer(stack.pop());
            }

            stack.push(i);
        }

        return ans;
    }
}
