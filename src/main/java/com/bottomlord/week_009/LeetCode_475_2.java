package com.bottomlord.week_009;

import java.util.Arrays;

public class LeetCode_475_2 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int max = 0;
        for (int house : houses) {
            int index = 0;
            while (index < heaters.length && heaters[index] < house) {
                index++;
            }
            if (index == 0) {
                max = Math.max(Math.abs(house - heaters[0]), max);
            } else if (index == heaters.length) {
                max = Math.max(Math.abs(house - heaters[index - 1]), max);
            } else {
                max = Math.max(Math.min(Math.abs(heaters[index] - house), house - heaters[index - 1]), max);
            }
        }

        return max;
    }
}