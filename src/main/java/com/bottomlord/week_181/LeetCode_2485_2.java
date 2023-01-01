package com.bottomlord.week_181;

import java.util.Map;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2023-01-01 13:47:07
 */
public class LeetCode_2485_2 {
    public int pivotInteger(int n) {
        double x = Math.sqrt((n * n + n) / 2.0);
        return x - (int) x > 0 ? -1 : (int) x;
    }
}