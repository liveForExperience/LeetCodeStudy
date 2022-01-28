package com.bottomlord.week_133;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-01-28 08:58:08
 */
public class LeetCode_1996_1_游戏中弱角色的数量 {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (x, y) -> x[1] == y[1] ? x[0] - y[0] : y[1] - x[1]);
        int maxDef = 0, ans = 0;
        for (int[] property : properties) {
            if (property[0] < maxDef) {
                ans++;
            } else {
                maxDef = property[0];
            }
        }

        return ans;
    }
}
