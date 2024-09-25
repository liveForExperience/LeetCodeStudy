package com.bottomlord.week_025;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ThinkPad
 * @date 2019/12/28 21:44
 */
public class LeetCode_384_1_打乱数组 {
    class Solution {
        private Random random = new Random();
        private int[] origin;

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

            int[] ans = new int[origin.length];
            for (int i = 0; i < origin.length; i++) {
                int index = random.nextInt(list.size());
                ans[i] = list.get(index);
                list.remove(index);
            }
            return ans;
        }
    }
}
