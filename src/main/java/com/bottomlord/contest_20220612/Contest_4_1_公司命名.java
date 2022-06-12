package com.bottomlord.contest_20220612;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-06-12 11:40:43
 */
public class Contest_4_1_公司命名 {
    public long distinctNames(String[] ideas) {
        Map<String, Integer> map = new HashMap<>();
        for (String idea : ideas) {
            String prefix = idea.substring(1);
            map.put(prefix, map.getOrDefault(prefix, 0) | (1 << idea.charAt(0) - 'a'));
        }

        long count = 0;
        List<String> list = new ArrayList<>(map.keySet());
        for (int i = 0; i < list.size(); i++) {
            int a = map.get(list.get(i));
            for (int j = 0; j < list.size(); j++) {
                int b = map.get(list.get(j));

                int same = Long.bitCount(a & b);
                count += (long) (Integer.bitCount(a) - same) * (Integer.bitCount(b) - same);
            }
        }

        return count;
    }
}
