package com.bottomlord.week_036;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/10 12:23
 */
public class Interview_61_1_扑克牌中的顺子 {
    public boolean isStraight(int[] nums) {
        int max = 0;
        int[] arr = new int[14];
        for (int num : nums) {
            arr[num]++;
            max = Math.max(max, num);
        }

        if (max == 0) {
            return true;
        }

        for (int i = max - 4; i <= max; i++) {
            if (arr[i]-- <= 0 && arr[0]-- <= 0) {
                return false;
            }
        }

        return true;
    }
}
