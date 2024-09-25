package com.bottomlord.week_191;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-03-09 18:05:20
 */
public class LeetCode_898_2 {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>(), cur = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            Set<Integer> tmp = new HashSet<>();
            cur.add(arr[i]);
            for (Integer num : cur) {
                tmp.add(num | arr[i]);
            }
            cur = tmp;
            ans.addAll(cur);
        }
        return ans.size();
    }
}