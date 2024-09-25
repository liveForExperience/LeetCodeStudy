package com.bottomlord.week_040;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/4/9 8:18
 */
public class Interview_1606_1_最小差 {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        long ans = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < a.length && j < b.length;) {
            ans = Math.min(Math.abs((long)a[i] - (long)b[j]), ans);
            if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        return (int)ans;
    }
}
