package com.bottomlord.week_044;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/5/9 8:22
 */
public class Interview_1711_2 {
    public int findClosest(String[] words, String word1, String word2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        int len = words.length;
        for (int i = 0; i < len; i++) {
            if (Objects.equals(words[i], word1)) {
                list1.add(i);
            }

            if (Objects.equals(words[i], word2)) {
                list2.add(i);
            }
        }

        if (list1.size() == 0 || list2.size() == 0) {
            return len;
        }

        int ans = len, i1 = 0, i2 = 0;
        while (i1 < list1.size() && i2 < list2.size()) {
            int num1 = list1.get(i1), num2 = list2.get(i2);
            ans = Math.min(ans, Math.abs(num1 - num2));

            if (num1 < num2) {
                i1++;
            } else {
                i2++;
            }
        }

        return ans;
    }
}