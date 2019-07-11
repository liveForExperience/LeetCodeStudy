package com.bottomlord.week_1;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/11 13:14
 */
public class LeetCode_1051_1_高度检查器 {
    public int heightChecker(int[] heights) {
        int[] tmp = Arrays.copyOf(heights, heights.length);
        Arrays.sort(tmp);
        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (tmp[i] != heights[i]) {
                count++;
            }
        }

        return count;
    }
}
