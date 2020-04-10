package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/10 8:06
 */
public class Interview_1611_2 {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }

        if (shorter == longer) {
            return new int[]{shorter * k};
        }

        int diff = longer - shorter;
        int[] ans = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            ans[i] = shorter * k + diff * i;
        }
        return ans;
    }
}