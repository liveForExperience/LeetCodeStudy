package com.bottomlord.week_098;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/5/27 8:40
 */
public class LeetCode_1086_2 {
    public int[][] highFive(int[][] items) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] item : items) {
            int id = item[0], score = item[1];
            int[] scores = map.getOrDefault(id, new int[101]);
            scores[score]++;
            map.put(id, scores);
        }

        int[][] ans = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int id = entry.getKey(), sum = 0;
            int[] scores = entry.getValue();
            for (int i = scores.length - 1, count = 5; i > 0 && count > 0; i--) {
                if (scores[i] > 0) {
                    while (scores[i] > 0 && count > 0) {
                        scores[i]--;
                        count--;
                        sum += i;
                    }
                }
            }

            ans[index][0] = id;
            ans[index][1] = sum / 5;
            index++;
        }

        return ans;
    }
}
