package com.bottomlord.week_114;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-09-14 08:25:06
 */
public class LeetCode_524_1_通过删除字母匹配到字典里最长单词 {
    public String findLongestWord(String s, List<String> dictionary) {
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            map.computeIfAbsent(cs[i], x -> new TreeSet<>()).add(i);
        }

        dictionary.sort((x, y) -> {
            if (x.length() == y.length()) {
                return x.compareTo(y);
            }

            return y.length() - x.length();
        });

        for (String word : dictionary) {
            boolean flag = true;
            int index = -1;
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c)) {
                    flag = false;
                    break;
                }

                TreeSet<Integer> set = map.get(c);
                Integer nextKey = set.ceiling(index);

                if (nextKey == null) {
                    flag = false;
                    break;
                }

                index = nextKey + 1;
            }

            if (flag) {
                return word;
            }
        }

        return "";
    }
}
