package com.bottomlord.week_030;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/1/31 9:16
 */
public class LeetCode_822_1_翻转卡片游戏 {
    public int flipgame(int[] fronts, int[] backs) {
        int len = fronts.length, ans = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (fronts[i] == backs[i]) {
                set.add(fronts[i]);
            }
        }

        for (int i = 0; i < len; i++) {
            int front = fronts[i];
            if (!set.contains(front)) {
                ans = Math.min(ans, front);
            }

            int back = backs[i];
            if (!set.contains(back)) {
                ans = Math.min(ans, back);
            }
        }

        return ans;
    }
}
