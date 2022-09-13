package com.bottomlord.week_166;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-09-13 20:15:06
 */
public class LeetCode_2363_1_合并相似的物品 {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        Arrays.sort(items1, Comparator.comparingInt(x -> x[0]));
        Arrays.sort(items2, Comparator.comparingInt(x -> x[0]));

        int i1 = 0, i2 = 0, l1 = items1.length, l2 = items2.length;
        List<List<Integer>> ans = new ArrayList<>();
        while (i1 < l1 || i2 < l2) {
            if (i1 == l1) {
                ans.add(getList(items2[i2][0], items2[i2][1]));
                i2++;
                continue;
            }

            if (i2 == l2) {
                ans.add(getList(items1[i1][0], items1[i1][1]));
                i1++;
                continue;
            }

            int v1 = items1[i1][0], v2 = items2[i2][0];
            if (v1 == v2) {
                ans.add(getList(items1[i1][0], items1[i1][1] + items2[i2][1]));
                i1++;
                i2++;
            } else if (v1 < v2) {
                ans.add(getList(items1[i1][0], items1[i1][1]));
                i1++;
            } else {
                ans.add(getList(items2[i2][0], items2[i2][1]));
                i2++;
            }
        }

        return ans;
    }

    private List<Integer> getList(int val, int weight) {
        List<Integer> list = new ArrayList<>();
        list.add(val);
        list.add(weight);
        return list;
    }
}
