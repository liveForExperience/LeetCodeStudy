package com.bottomlord.week_083;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/2/8 15:13
 */
public class LeetCode_446_1_等差数列划分II子数组 {
    private int[] arr;
    private int len, ans;
    public int numberOfArithmeticSlices(int[] A) {
        this.arr = A;
        this.len = A.length;
        dfs(new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(List<Integer> list, int depth) {
        if (depth == len) {
            if (list.size() < 3) {
                return;
            }

            int diff = list.get(1) - list.get(0);
            for (int i = 2; i < list.size(); i++) {
                if (list.get(i) - list.get(i - 1) != diff) {
                    return;
                }
            }
            ans++;
            return;
        }

        dfs(list, depth + 1);
        list.add(arr[depth]);
        dfs(list, depth + 1);
        list.remove(list.size() - 1);
    }
}
