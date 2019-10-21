package com.bottomlord.week_016;

import java.util.Random;

public class LeetCode_398_1_随机数索引 {
    class Solution {
        private int[] nums;
        public Solution(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            Random r = new Random();
            int count = 0, index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    count++;

                    if (r.nextInt() % count == 0) {
                        index = i;
                    }
                }
            }
            return index;
        }
    }
}