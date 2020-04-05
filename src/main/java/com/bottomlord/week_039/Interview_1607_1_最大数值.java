package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/5 21:44
 */
public class Interview_1607_1_最大数值 {
    public int maximum(int a, int b) {
        int bit = (int) (((long)a - (long)b) >>> 63);
        return a * (bit ^ 1) + b * bit;
    }
}
