package com.bottomlord.week_201;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-05-17 22:08:35
 */
public class LeetCode_2682_1_找出转圈游戏输家 {
    public int[] circularGameLosers(int n, int k) {
        boolean[] memo = new boolean[n];
        int index = 0;
        for (int i = 0; ; i++) {
            if (memo[index]) {
                break;
            }
            memo[index] = true;
            index = (index + (i + 1) * k) % n;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < memo.length; i++) {
            if (memo[i]) {
                continue;
            }

            list.add(i + 1);
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
