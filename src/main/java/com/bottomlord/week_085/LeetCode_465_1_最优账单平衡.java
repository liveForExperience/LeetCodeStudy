package com.bottomlord.week_085;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/2/24 8:43
 */
public class LeetCode_465_1_最优账单平衡 {
    private int ans = Integer.MAX_VALUE;
    public int minTransfers(int[][] transactions) {
        int len = transactions.length;
        Map<Integer, Integer> sum = new HashMap<>();
        for (int i = 0; i < len; i++) {
            sum.put(transactions[i][0], sum.getOrDefault(transactions[i][0], 0) + transactions[i][2]);
            sum.put(transactions[i][1], sum.getOrDefault(transactions[i][1], 0) - transactions[i][2]);
        }

        List<Integer> list = new ArrayList<>();
        for (Integer key : sum.keySet()) {
            if (sum.get(key) != 0) {
                list.add(sum.get(key));
            }
        }

        dfs(0, 0, list);
        return ans;
    }

    private void dfs(int index, int count, List<Integer> list) {
        if (count > ans) {
            return;
        }

        while (index < list.size() && list.get(index) == 0) {
            index++;
        }

        if (index == list.size()) {
            ans = Math.min(ans, count);
            return;
        }

        for (int i = index + 1; i < list.size(); i++) {
            if (list.get(index) * list.get(i) < 0) {
                list.set(i, list.get(i) + list.get(index));
                dfs(index + 1, count + 1, list);
                list.set(i, list.get(i) - list.get(index));
            }
        }
    }
}
