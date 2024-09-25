package com.bottomlord.week_189;

/**
 * @author chen yue
 * @date 2023-02-24 19:28:15
 */
public class LeetCode_755_1_倒水 {
    public int[] pourWater(int[] heights, int volume, int k) {
        while (volume-- > 0) {
            int choice = k;
            for (int i = k - 1; i >= 0; i--) {
                if (heights[i] < heights[i + 1]) {
                    choice = i;
                } else if (heights[i] > heights[i + 1]) {
                    break;
                }
            }

            if (choice != k) {
                heights[choice]++;
                continue;
            }

            for (int i = k + 1; i < heights.length; i++) {
                if (heights[i] < heights[i - 1]) {
                    choice = i;
                } else if (heights[i] > heights[i - 1]) {
                    break;
                }
            }

            heights[choice]++;
        }

        return heights;
    }
}
