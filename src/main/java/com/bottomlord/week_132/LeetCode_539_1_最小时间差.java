package com.bottomlord.week_132;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-01-18 08:52:39
 */
public class LeetCode_539_1_最小时间差 {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> list = new ArrayList<>();
        for (String timePoint : timePoints) {
            list.add(convert(timePoint));
        }

        list.sort(Comparator.comparingInt(x -> x));

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            int next = list.get((i + 1) % list.size());
            min = Math.min(min, (next - list.get(i) + 1440) % 1440);
        }

        return min;
    }

    private Integer convert(String str) {
        String[] strs = str.split(":");
        return Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
    }
}
