package com.bottomlord.week_269;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-09-03 08:59:57
 */
public class LeetCode_2708_1_一个小组的最大实力值 {
    public long maxStrength(int[] nums) {
        List<Integer> ps = new ArrayList<>(), ns = new ArrayList<>();
        boolean hasZero = false;
        for (int num : nums) {
            if (num > 0) {
                ps.add(num);
            } else if (num < 0) {
                ns.add(num);
            } else {
                hasZero = true;
            }
        }

        if (ps.isEmpty() && ns.isEmpty()) {
            return 0;
        }

        long sum = 1L;
        for (Integer p : ps) {
            sum *= p;
        }

        if (ps.isEmpty() && ns.size() == 1) {
            return hasZero ? 0 : ns.get(0);
        }

        int i = ns.size() - 1;
        if (ns.size() % 2 == 1) {
            Collections.sort(ns);
            i = ns.size() - 2;
        }

        for (; i >= 0; i--) {
            sum *= ns.get(i);
        }

        return sum;
    }
}
