package com.bottomlord.week_098;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/5/29 22:04
 */
public class LeetCode_1119_1_删去字符串中的元音 {
    public String removeVowels(String s) {
        Character[] cs = new Character[]{'a', 'e', 'i', 'o', 'u'};
        Set<Character> set = Arrays.stream(cs).collect(Collectors.toSet());

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!set.contains(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
