package com.bottomlord.week_200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-05-09 22:38:52
 */
public class LeetCode_967_1_连续差相同的数字 {
    private int k, n;
    public int[] numsSameConsecDiff(int n, int k) {
        this.k = k;
        this.n = n;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            dfs(0, i, i, list);
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(int index, int num, int pre, List<Integer> list) {
        if (index >= n) {
            list.add(num);
            return;
        }

        if (pre + k <= 9) {
            dfs(index + 1, num * 10 + pre + k, pre + k, list);
        }

        if (pre - k >= 0 && pre - k != pre + k) {
            dfs(index + 1, num * 10 + pre - k, pre - k, list);
        }
    }
}
