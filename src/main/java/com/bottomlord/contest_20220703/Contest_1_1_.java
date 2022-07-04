package com.bottomlord.contest_20220703;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-07-02 23:28:55
 */
public class Contest_1_1_ {
    public String decodeMessage(String key, String message) {
        Set<Character> memo = new HashSet<>();
        char[] keys = key.toCharArray();
        char[] cs = new char[26];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = (char)(i + 'a');
        }
        int index = 0;
        for (char c : keys) {
            if (c == ' ' || memo.contains(c)) {
                continue;
            }

            if (index == 26) {
                break;
            }

            cs[index++] = c;
            memo.add(c);
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < cs.length; i++) {
            map.put(cs[i], i);
        }

        StringBuilder sb = new StringBuilder();
        char[] ms = message.toCharArray();

        for (char m : ms) {
            if (m == ' ') {
                sb.append(m);
                continue;
            }

            sb.append((char)(map.get(m) + 'a'));
        }

        return sb.toString();
    }
}
