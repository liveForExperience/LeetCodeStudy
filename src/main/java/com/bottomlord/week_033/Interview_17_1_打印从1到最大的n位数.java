package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/19 12:23
 */
public class Interview_17_1_打印从1到最大的n位数 {
    public int[] printNumbers(int n) {
        int max = 1;
        while (n-- > 0) {
            max *= 10;
        }

        int[] ans = new int[max - 1];
        for (int i = 1; i < max; i++) {
            ans[i - 1] = i;
        }
        return ans;
    }
}
