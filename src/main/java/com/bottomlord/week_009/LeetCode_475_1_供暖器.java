package com.bottomlord.week_009;

public class LeetCode_475_1_供暖器 {
    public int findRadius(int[] houses, int[] heaters) {
        int max = 0;
        for (int house : houses) {
            int min = Integer.MAX_VALUE;
            for (int heater: heaters) {
                min = Math.min(Math.abs(heater - house), min);
            }
            max = Math.max(min, max);
        }
        return max;
    }
}
