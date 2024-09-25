package com.bottomlord.week_099;

import java.util.Arrays;

public class LeetCode_LCP18_2 {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int mod = 1000000007;

        Arrays.sort(staple);
        Arrays.sort(drinks);

        int ans = 0;
        for (int s : staple) {
            int j = drinks.length - 1;
            while (j >= 0 && s + drinks[j] > x) {
               j--;
            }

            if (j < 0) {
                break;
            }

            ans = (ans + j + 1) % mod;
        }

        return ans;
    }
}
