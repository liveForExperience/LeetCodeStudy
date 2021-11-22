package com.bottomlord.week_124;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chen yue
 * @date 2021-11-22 09:01:30
 */
public class LeetCode_384_2 {
    class Solution {
        int[] origin;
        public Solution(int[] nums) {
            this.origin = nums;
        }

        public int[] reset() {
            return origin;
        }

        public int[] shuffle() {
            int[] copy = new int[origin.length], nums = new int[origin.length];
            System.arraycopy(origin, 0, copy, 0, origin.length);

            Random ran = new Random();
            for (int i = 0; i < origin.length; i++) {
                int j = ran.nextInt(origin.length - i);
                nums[i] = i + copy[j];
                int tmp = copy[i];
                copy[i] = copy[j];
                copy[j] = tmp;
            }

            return nums;
        }
    }
}
