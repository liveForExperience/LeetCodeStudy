package com.bottomlord.week_116;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-09-28 22:11:51
 */
public class LeetCode_1742_1_盒子中小球的最大数量 {
    public int countBalls(int lowLimit, int highLimit) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int n = i, sum = 0;
            while (n > 0) {
                int num = n % 10;
                sum += num;
                n /= 10;
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
            max = Math.max(max, map.get(sum));
        }

        return max;
    }
}
