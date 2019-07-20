package com.bottomlord.week_2;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * @author LiveForExperience
 * @date 2019/7/20 21:50
 */
public class LeetCode_1002_1_查找常用字符 {
    public List<String> commonChars(String[] A) {
        Map<Character, Integer> map = null;

        for (String str: A) {
            Map<Character, Integer> tmp = new HashMap<>();
            for (char c : str.toCharArray()) {
                if (tmp.containsKey(c)) {
                    tmp.computeIfPresent(c, (k, v) -> v += 1);
                } else {
                    tmp.put(c, 1);
                }
            }

            if (map == null) {
                map = tmp;
                continue;
            }

            for (Character c: map.keySet()) {
                if (tmp.containsKey(c)) {
                    map.computeIfPresent(c, (k, v) -> v = Math.min(v, tmp.get(c)));
                } else {
                    map.computeIfPresent(c, (k, v) -> v = 0);
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            int count = entry.getValue();
            while (count-- > 0) {
                list.add(String.valueOf(entry.getKey()));
            }
        }

        return list;
    }

    public static void main(String[] args) {
        LeetCode_1002_1_查找常用字符 test = new LeetCode_1002_1_查找常用字符();
        test.commonChars(new String[]{"cool","lock","cook"});
    }
}
