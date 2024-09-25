package com.bottomlord.week_106;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/7/20 8:24
 */
public class LeetCode_1346_1_检查整数及其两倍数是否存在 {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        for (int num : set) {
            if (set.contains(num * 2)) {
                return true;
            }
        }

        return false;
    }
}
