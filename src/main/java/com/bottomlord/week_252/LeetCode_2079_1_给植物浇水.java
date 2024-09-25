package com.bottomlord.week_252;

/**
 * @author chen yue
 * @date 2024-05-09 08:46:16
 */
public class LeetCode_2079_1_给植物浇水 {
    public int wateringPlants(int[] plants, int capacity) {
        int left = capacity, step = 0;
        for (int i = 0; i < plants.length; i++) {
            int plant = plants[i];
            if (plant > left) {
                step += i * 2 + 1;
                left = capacity;
            } else {
                step++;
            }

            left -= plant;
        }

        return step;
    }
}
