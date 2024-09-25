package com.bottomlord.week_124;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chen yue
 * @date 2021-11-22 08:43:07
 */
public class LeetCode_384_1_打乱数组 {
    class Solution {
        int[] origin;
        public Solution(int[] nums) {
            this.origin = nums;
        }

        public int[] reset() {
            return origin;
        }

        public int[] shuffle() {
            List<Integer> list = new ArrayList<>();
            for (int num : origin) {
                list.add(num);
            }

            int[] nums = new int[origin.length];
            Random ran = new Random();
            for (int i = 0; i < origin.length; i++) {
                int index = ran.nextInt(list.size());
                nums[i] = list.get(index);
                list.remove(index);
            }

            return nums;
        }
    }
}
