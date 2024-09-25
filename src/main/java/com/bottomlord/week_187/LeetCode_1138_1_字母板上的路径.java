package com.bottomlord.week_187;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-02-12 21:09:01
 */
public class LeetCode_1138_1_字母板上的路径 {
    public String alphabetBoardPath(String target) {
        Map<Character, int[]> map = new HashMap<>(26);
        map.put('a', new int[]{0, 0});
        map.put('b', new int[]{0, 1});
        map.put('c', new int[]{0, 2});
        map.put('d', new int[]{0, 3});
        map.put('e', new int[]{0, 4});
        map.put('f', new int[]{1, 0});
        map.put('g', new int[]{1, 1});
        map.put('h', new int[]{1, 2});
        map.put('i', new int[]{1, 3});
        map.put('j', new int[]{1, 4});
        map.put('k', new int[]{2, 0});
        map.put('l', new int[]{2, 1});
        map.put('m', new int[]{2, 2});
        map.put('n', new int[]{2, 3});
        map.put('o', new int[]{2, 4});
        map.put('p', new int[]{3, 0});
        map.put('q', new int[]{3, 1});
        map.put('r', new int[]{3, 2});
        map.put('s', new int[]{3, 3});
        map.put('t', new int[]{3, 4});
        map.put('u', new int[]{4, 0});
        map.put('v', new int[]{4, 1});
        map.put('w', new int[]{4, 2});
        map.put('x', new int[]{4, 3});
        map.put('y', new int[]{4, 4});
        map.put('z', new int[]{5, 0});

        char pre = 'a';
        char[] cs = target.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (map.get(pre)[0] == map.get(c)[0] &&
                    map.get(pre)[1] == map.get(c)[1]) {
            } else {
                boolean z = false;
                if (c == 'z') {
                    c = 'u';
                    z = true;
                }

                if (pre == 'z') {
                    pre = 'u';
                    sb.append("U");
                }

                int x = map.get(c)[0] - map.get(pre)[0],
                    y = map.get(c)[1] - map.get(pre)[1];

                if (x < 0) {
                    int n = -x;
                    for (int i = 0; i < n; i++) {
                        sb.append("U");
                    }
                } else {
                    for (int i = 0; i < x; i++) {
                        sb.append("D");
                    }
                }

                if (y < 0) {
                    int n = -y;
                    for (int i = 0; i < n; i++) {
                        sb.append("L");
                    }
                } else {
                    for (int i = 0; i < y; i++) {
                        sb.append("R");
                    }
                }

                if (z) {
                    sb.append("D");
                    c = 'z';
                }
            }

            sb.append("!");
            pre = c;
        }

        return sb.toString();
    }
}
