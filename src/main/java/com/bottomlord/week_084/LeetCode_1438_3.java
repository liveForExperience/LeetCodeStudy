package com.bottomlord.week_084;

import java.util.ArrayDeque;

/**
 * @author ChenYue
 * @date 2021/2/21 21:07
 */
public class LeetCode_1438_3 {
    public int longestSubarray(int[] nums, int limit) {
        ArrayDeque<Integer> maxQ = new ArrayDeque<>(), minQ = new ArrayDeque<>();
        int len = nums.length, ans = 0, l = 0;
        for (int r = 0; r < len; r++) {
            while (!maxQ.isEmpty() && maxQ.peekLast() < nums[r]) {
                maxQ.pollLast();
            }
            while (!minQ.isEmpty() && minQ.peekLast() < nums[r]) {
                minQ.pollLast();
            }
            maxQ.offerLast(nums[r]);
            minQ.offerLast(nums[r]);

            while(!minQ.isEmpty() && !maxQ.isEmpty() && maxQ.peekFirst() - minQ.peekFirst() > limit) {
                if (maxQ.peekFirst() == nums[l]) {
                    maxQ.pollFirst();
                }

                if (minQ.peekFirst() == nums[l]) {
                    minQ.pollFirst();
                }

                l++;
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }
}
