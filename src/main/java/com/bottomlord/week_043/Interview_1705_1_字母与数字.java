package com.bottomlord.week_043;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/4/28 8:49
 */
public class Interview_1705_1_字母与数字 {
    public String[] findLongestSubarray(String[] array) {
        int max = 0, sum = 0, start = 0, end = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            for (char c : array[i].toCharArray()) {
                if (Character.isDigit(c)) {
                    sum++;
                    break;
                } else {
                    sum--;
                    break;
                }
            }

            if (sum == 0) {
                start = 0;
                end = i;
                max = i + 1;
                continue;
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                if (max < i - map.get(sum)) {
                    start = map.get(sum) + 1;
                    end = i;
                    max = i - map.get(sum) + 1;
                }
            }
        }

        if (start == 0) {
            return Arrays.copyOf(array, max);
        }

        return Arrays.copyOfRange(array, start, end + 1);
    }
}
