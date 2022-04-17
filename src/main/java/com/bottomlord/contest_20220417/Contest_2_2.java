package com.bottomlord.contest_20220417;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-04-17 20:45:40
 */
public class Contest_2_2 {
    public int minimumRounds(int[] tasks) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (val == 1) {
                return -1;
            }
            sum += count(val);
        }

        return sum;
    }

    private int count(int num) {
        return (num / 3) + (num % 3 >= 1 ? 1 : 0);
    }
}
