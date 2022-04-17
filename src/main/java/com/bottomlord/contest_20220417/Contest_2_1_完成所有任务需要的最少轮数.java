package com.bottomlord.contest_20220417;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-04-17 10:40:51
 */
public class Contest_2_1_完成所有任务需要的最少轮数 {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = count(entry.getValue());
            if (val == Integer.MAX_VALUE) {
                return -1;
            }

            ans += val;
        }

        return ans;
    }

    private int count(int val) {
        int c2 = val / 2, min = Integer.MAX_VALUE;
        for (int i = 0; i <= c2; i++) {
            int num = val - i * 2;
            if (num % 3 != 0) {
                continue;
            }

            int count = i + num / 3;

            if (count < min) {
                min = count;
                break;
            }
        }

        return min;
    }
}
