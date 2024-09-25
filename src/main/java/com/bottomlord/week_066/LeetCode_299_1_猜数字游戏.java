package com.bottomlord.week_066;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/10/14 8:08
 */
public class LeetCode_299_1_猜数字游戏 {
    public String getHint(String secret, String guess) {
        char[] scs = secret.toCharArray(),
               gcs = guess.toCharArray();

        Map<Character, List<Integer>> sMap = new HashMap<>();
        for (int i = 0; i < scs.length; i++) {
            List<Integer> list = sMap.getOrDefault(scs[i], new ArrayList<>());
            list.add(i);
            sMap.put(scs[i], list);
        }

        int bulls = 0, cows = 0;
        for (int i = 0; i < gcs.length; i++) {
            char c = gcs[i];
            if (sMap.containsKey(c) && sMap.get(c).contains(i)) {
                bulls++;
                sMap.get(c).remove(new Integer(i));

                if (sMap.get(c).size() == 0) {
                    sMap.remove(c);
                }
                gcs[i] = '-';
            }
        }

        for (int i = gcs.length - 1; i >= 0; i--) {
            char c = gcs[i];
            if (c == '-') {
                continue;
            }

            if (sMap.containsKey(c)) {
                cows++;
                sMap.get(c).remove(0);

                if (sMap.get(c).size() == 0) {
                    sMap.remove(c);
                }
            }
        }

        return bulls + "A" + cows + "B";
    }
}
