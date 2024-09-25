package com.bottomlord.week_199;

import java.util.LinkedList;

/**
 * @author chen yue
 * @date 2023-05-03 14:01:45
 */
public class LeetCode_LCP72_1_补给马车 {
    public int[] supplyWagon(int[] supplies) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int supply : supplies) {
            list.add(supply);
        }

        int n = supplies.length / 2;
        while (list.size() > n) {
            int min = Integer.MAX_VALUE, target = -1;
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < min) {
                    min = sum;
                    target = i;
                }
            }

            list.set(target, min);
            list.remove(target + 1);
        }

        int[] ans = new int[n];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
