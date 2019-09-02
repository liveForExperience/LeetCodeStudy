package com.bottomlord.week_008;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_970_2 {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> ans = new ArrayList<>();
        if (bound < 2) {
            return ans;
        }

        List<Integer> xs = getPowList(x, bound);
        List<Integer> ys = getPowList(y, bound);
        for (Integer xSum : xs) {
            for (Integer ySum : ys) {
                int sum = xSum + ySum;
                if (sum > bound) {
                    break;
                }

                if (!ans.contains(sum)) {
                    ans.add(sum);
                }
            }
        }
        return ans;
    }

    private List<Integer> getPowList(int num, int bound) {
        List<Integer> list = new ArrayList<>();
        if (num == 1) {
            list.add(1);
            return list;
        }
        int i = 0;
        while (Math.pow(num, i) <= bound) {
            list.add((int)Math.pow(num, i++));
        }
        return list;
    }
}