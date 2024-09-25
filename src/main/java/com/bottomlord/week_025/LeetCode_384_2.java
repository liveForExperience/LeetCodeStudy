package com.bottomlord.week_025;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ThinkPad
 * @date 2019/12/29 10:01
 */
public class LeetCode_384_2 {
    class Solution {
        private Random random = new Random();
        private int[] origin;
        private int[] arr;

        public Solution(int[] nums) {
            this.origin = nums;
            this.arr = origin.clone();
        }

        public int[] reset() {
            arr = origin;
            origin = origin.clone();

            return origin;
        }

        public int[] shuffle() {
            for (int i = 0; i < origin.length; i++) {
                swap(i, random.nextInt(arr.length - i) + i);
            }
            return arr;
        }

        private void swap(int a, int b) {
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }
    }
}
