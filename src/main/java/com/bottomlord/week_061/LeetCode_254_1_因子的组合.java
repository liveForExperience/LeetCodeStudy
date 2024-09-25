package com.bottomlord.week_061;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/9/5 10:01
 */
public class LeetCode_254_1_因子的组合 {
    public List<List<Integer>> getFactors(int n) {
        return dfs(2, n);
    }

    private List<List<Integer>> dfs(int factor, int num) {
        if (num <= 1) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = factor; i * i <= num; i++) {
            if (num % i == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(num / i);
                ans.add(list);
                List<List<Integer>> next = dfs(i, num / i);

                for (List<Integer> nextList : next) {
                    nextList.add(i);
                    ans.add(nextList);
                }
            }
        }

        return ans;
    }
}
