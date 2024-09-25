package com.bottomlord.week_078;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/1/6 11:57
 */
public class LeetCode_1710_1_卡车上的最大单元数 {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (x, y) -> y[1] - x[1]);
        int sum = 0;
        for (int[] boxType : boxTypes) {
            if (truckSize <= 0) {
                break;
            }

            int cost = Math.min(boxType[0], truckSize);
            truckSize -= cost;
            sum += cost * boxType[1];
        }

        return sum;
    }
}
