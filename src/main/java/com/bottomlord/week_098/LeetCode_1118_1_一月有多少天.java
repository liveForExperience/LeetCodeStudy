package com.bottomlord.week_098;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author ChenYue
 * @date 2021/5/28 8:56
 */
public class LeetCode_1118_1_一月有多少天 {
    public int numberOfDays(int Y, int M) {
        Map<Integer, Function<Integer, Integer>> map = new HashMap<>();
        int[] thirtyOneDaysMonths = new int[]{1, 3, 5, 7, 8, 10, 12};
        int[] thirtyDaysMonths = new int[]{4, 6, 9, 11};
        Arrays.stream(thirtyDaysMonths).forEach(x -> map.put(x, y -> 30));
        Arrays.stream(thirtyOneDaysMonths).forEach(x -> map.put(x, y -> 31));
        map.put(2, x -> {
            if (x % 100 == 0) {
                return x % 400 == 0 ? 29 : 28;
            }
            return x % 4 == 0 ? 29 : 28;
        });
        return map.get(M).apply(Y);
    }
}
