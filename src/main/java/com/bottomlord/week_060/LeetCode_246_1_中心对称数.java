package com.bottomlord.week_060;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/8/28 8:26
 */
public class LeetCode_246_1_中心对称数 {
    public boolean isStrobogrammatic(String num) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);

        int head = 0, tail = num.length() - 1;
        while (head < tail) {
            int a = num.charAt(head) - '0',
                b = num.charAt(tail) - '0';

            if (!map.containsKey(a) || !map.containsKey(b)) {
                return false;
            }

            if (a == b && (a == 6 || a == 9)) {
                return false;
            }

            if (a != b && ((a != 6 && a != 9) || (a == 6 && b != 9) || (a == 9 && b != 6))) {
                return false;
            }

            head++;
            tail--;
        }

        return true;
    }
}
