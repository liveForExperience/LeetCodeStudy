package com.bottomlord.week_069;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/11/6 23:50
 */
public class LeetCode_1356_1_根据数字二进制下1的数目排序 {
    class Solution {
        public int[] sortByBits(int[] arr) {
            int[] nums = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                nums[i] = count(arr[i]);
            }

            Integer[] order = new Integer[arr.length];
            for (int i = 0; i < order.length; i++) {
                order[i] = i;
            }

            Arrays.sort(order, (x, y) -> {
                if (nums[x] == nums[y]) {
                    return arr[x] - arr[y];
                }

                return nums[x] - nums[y];
            });

            int[] ans = new int[nums.length];
            for (int i = 0; i < order.length; i++) {
                ans[i] = arr[order[i]];
            }
            return ans;
        }

        private int count(int num) {
            int count = 0;
            while (num > 0) {
                num = num & (num - 1);
                count++;
            }

            return count;
        }
    }
}
