package com.bottomlord.week_181;

/**
 * @author chen yue
 * @date 2023-01-01 13:13:09
 */
public class LeetCode_2469_1_温度转换 {
    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.8 + 32};
    }
}
