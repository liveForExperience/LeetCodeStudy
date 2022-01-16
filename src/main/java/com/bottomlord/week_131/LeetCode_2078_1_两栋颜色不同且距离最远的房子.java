package com.bottomlord.week_131;

/**
 * @author chen yue
 * @date 2022-01-16 21:27:06
 */
public class LeetCode_2078_1_两栋颜色不同且距离最远的房子 {
    public int maxDistance(int[] colors) {
        int n = colors.length, max = 0;
        boolean[] m = new boolean[n];
        for (int i = 0; i < colors.length; i++) {
            if (m[colors[i]]) {
                continue;
            }

            m[colors[i]] = true;

            for (int j = n - 1; j > i; j--) {
                if (colors[j] != colors[i]) {
                    max = Math.max(max, Math.abs(i - j));
                    break;
                }
            }
        }

        return max;
    }
}
