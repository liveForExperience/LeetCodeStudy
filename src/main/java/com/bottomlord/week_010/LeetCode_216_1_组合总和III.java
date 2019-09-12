package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_216_1_组合总和III {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        int max = Math.min(n - (k * (k - 1) / 2), 9);
        backTreck(ans, new LinkedList<>(), k, n, max, 1, 0, 0);
        return ans;
    }

    private void backTreck(List<List<Integer>> ans, List<Integer> list, int k, int n, int max, int num, int sum, int time) {
        if (time == k && sum == n) {
            ans.add(new ArrayList<>(list));
            return;
        } else if (sum >= n) {
            return;
        }

        for (int j = num; j <= max; j++) {
            list.add(j);
            backTreck(ans, list, k, n, max, j + 1, sum + j, time + 1);
            list.remove(list.size() - 1);
        }
    }
}
