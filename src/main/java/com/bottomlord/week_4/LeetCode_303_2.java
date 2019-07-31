package com.bottomlord.week_4;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiveForExperience
 * @date 2019/7/31 21:02
 */
public class LeetCode_303_2 {
    class NumArray {
        private int[] sum;
        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }
}
