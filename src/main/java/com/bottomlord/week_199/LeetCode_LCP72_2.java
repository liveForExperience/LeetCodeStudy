package com.bottomlord.week_199;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-05-03 14:16:20
 */
public class LeetCode_LCP72_2 {
    public int[] supplyWagon(int[] supplies) {
        int n = supplies.length / 2;
        for (int i = 0; i < supplies.length - n; i++) {
            int index = 1, min = Integer.MAX_VALUE;
            for (int j = 1; j < supplies.length - i; j++) {
                int sum = supplies[j - 1] + supplies[j];
                if (sum < min) {
                    index = j;
                    min = sum;
                }
            }

            supplies[index - 1] = min;
            System.arraycopy(supplies, index + 1, supplies, index, supplies.length - index - 1);
        }

        return Arrays.copyOfRange(supplies, 0, n);
    }

    public static void main(String[] args) {
        LeetCode_LCP72_2 t = new LeetCode_LCP72_2();
        t.supplyWagon(new int[]{7,3,6,1,8});
    }
}
