package com.bottomlord.week_199;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-05-02 09:47:56
 */
public class LeetCode_970_1_强整数 {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        int v1 = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 21; i++) {
            int v2 = 1;
            for (int j = 0; j < 21; j++) {
                if (v1 + v2 <= bound) {
                    set.add(v1 + v2);
                } else {
                    break;
                }

                v2 *= y;
            }

            v1 *= x;

            if (v1 > bound) {
                break;
            }
        }

        return new ArrayList<>(set);
    }
}
