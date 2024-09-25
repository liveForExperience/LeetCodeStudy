package com.bottomlord.week_112;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-30 08:56:51
 */
public class LeetCode_1560_1_圆形赛道上经过次数最多的扇区 {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] bucket = new int[n + 1];
        bucket[rounds[0]]++;
        for (int i = 0; i < rounds.length - 1; i++) {
            int index = rounds[i], target = rounds[i + 1];
            index++;
            if (index > n) {
                index = 1;
            }
            while (index != target) {
                bucket[index++]++;
                if (index > n) {
                    index = 1;
                }
            }
            bucket[target]++;
        }

        int max = Arrays.stream(bucket).max().getAsInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == max) {
                list.add(i);
            }
        }
        return list;
    }
}
