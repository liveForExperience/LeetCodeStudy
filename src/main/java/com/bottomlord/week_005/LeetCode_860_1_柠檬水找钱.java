package com.bottomlord.week_005;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_860_1_柠檬水找钱 {
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(5, 0);
        map.put(10, 0);
        map.put(20, 0);

        for (int num: bills) {
            map.put(num, map.get(num) + 1);

            if (num == 5) {
                continue;
            }

            int five = map.get(5);
            int ten = map.get(10);

            if (num == 10) {
                if (five >= 1) {
                    map.put(5, five - 1);
                    continue;
                }

                return false;
            }

            if (num == 20) {
                if (ten >= 1 && five >= 1) {
                    map.put(5, five - 1);
                    map.put(10, ten - 1);
                    continue;
                }

                if (five >= 3) {
                    map.put(5, five - 3);
                    continue;
                }

                return false;
            }
        }

        return true;
    }
}
