package com.bottomlord.week_089;

import java.util.Random;

/**
 * @author ChenYue
 * @date 2021/3/22 8:29
 */
public class LeetCode_497_1_非重叠矩形中的随机点 {
    class Solution {
        private Random random = new Random();
        private int totalNum, len;
        private int[] pointNumSums;
        private int[][] rects;

        public Solution(int[][] rects) {
            this.rects = rects;
            this.len = rects.length;
            this.pointNumSums = new int[len];
            int index = 0;
            for (int[] rect : rects) {
                int width = rect[2] - rect[0] + 1,
                    height = rect[3] - rect[1] + 1;
                totalNum += width * height;
                pointNumSums[index++] = totalNum;
            }
        }

        public int[] pick() {
            int r = random.nextInt(totalNum);

            int head = 0, tail = len - 1;
            while (head < tail) {
                int mid = head + (tail - head) / 2;

                if (r >= pointNumSums[mid]) {
                    head = mid + 1;
                } else {
                    tail = mid;
                }
            }

            int[] rect = rects[head];
            int width = rect[2] - rect[0] + 1,
                height = rect[3] - rect[1] + 1,
                base = pointNumSums[head] - width * height;

            return new int[]{rect[0] + (r - base) % width, rect[1] + (r - base) / width};
        }
    }
}
