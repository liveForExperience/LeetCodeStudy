package com.bottomlord.week_128;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-12-20 08:47:17
 */
public class LeetCode_475_1_供暖器 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int max = 0, index = 0;
        for (int house : houses) {
            while (index < heaters.length && heaters[index] < house) {
                index++;
            }

            if (index == 0) {
                max = Math.max(max, heaters[index] - house);
            } else if (index == heaters.length) {
                max = Math.max(max, house - heaters[index - 1]);
            } else {
                max = Math.max(max, Math.min(heaters[index] - house, house - heaters[index - 1]));
            }
        }

        return max;
    }
}
