package com.bottomlord.week_060;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/8/26 8:54
 */
public class LeetCode_244_2 {
    private Map<String, List<Integer>> map;
    public LeetCode_244_2(String[] words) {
        this.map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            List<Integer> list = map.getOrDefault(words[i], new ArrayList<>());
            list.add(i);
            map.put(words[i], list);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1),
                l2 = map.get(word2);

        int i1 = 0, i2 = 0, ans = Integer.MAX_VALUE;
        while (i1 < l1.size() && i2 < l2.size()) {
            ans = Math.min(ans, Math.abs(l1.get(i1) - l2.get(i2)));

            if (l1.get(i1) > l2.get(i2)) {
                i2++;
            } else {
                i1++;
            }
        }

        return ans;
    }
}
