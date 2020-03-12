package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/12 8:25
 */
public class Interview_66_1_构建乘积数组 {
    public int[] constructArr(int[] a) {
        int len = a.length, left = 1, right = 1;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = left;
            left *= a[i];
        }

        for (int i = len - 1; i >= 0; i--) {
            ans[i] *= right;
            right *= a[i];
        }

        return ans;
    }
}
