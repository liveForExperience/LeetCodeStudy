package com.bottomlord.week_188;

import com.bottomlord.contest_20191027.CustomFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-02-18 10:22:37
 */
public class LeetCode_1270_1_找出给定方程的正整数解 {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            int j = binarySearch(i, customfunction, z);
            if (j == 0) {
                return ans;
            } else if (j != -1) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(j);
                ans.add(list);
            }
        }

        return ans;
    }

    private int binarySearch(int x, CustomFunction function, int z) {
        int head = 1, tail = 1000;
        while (head <= tail) {
            int mid = (head + tail) >> 1;
            int r = function.f(x, mid);
            if (r < z) {
                head = mid + 1;
            } else if (r > z) {
                tail = mid - 1;
            } else {
                return mid;
            }
        }

        return tail == 0 ? 0 : -1;
    }
}
