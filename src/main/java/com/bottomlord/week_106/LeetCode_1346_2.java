package com.bottomlord.week_106;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/7/20 8:29
 */
public class LeetCode_1346_2 {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int zero = 0;
        for (int num : arr) {
            if (num == 0) {
                zero++;
                if (zero > 1) {
                    return true;
                }
            }

            set.add(num);
        }

        for (int num : set) {
            if (num == 0) {
                continue;
            }

            if (set.contains(num * 2)) {
                return true;
            }
        }

        return false;
    }
}
