package com.bottomlord.week_178;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-12-09 16:15:45
 */
public class LeetCode_2418_1_按身高排序 {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        Arrays.sort(indexes, (x, y) -> heights[y] - heights[x]);
        String[] ans = new String[n];
        for (int i = 0; i < indexes.length; i++) {
            ans[i] = names[indexes[i]];
        }
        return ans;
    }
}
