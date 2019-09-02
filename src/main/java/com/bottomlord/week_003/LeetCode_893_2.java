package com.bottomlord.week_003;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/24 20:53
 */
public class LeetCode_893_2 {
    private final static int[] RECORD = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    public int numSpecialEquivGroups(String[] A) {
        Set<Integer> ans = new HashSet<>();
        for (String str : A) {
            ans.add(hash(str));
        }
        return ans.size();
    }

    private int hash(String str) {
        int res = 1;
        for (int i = 0; i < str.length(); i += 2) {
            int idx = str.charAt(i) - 'a';
            res *= RECORD[idx];
        }

        res += 5000;

        for (int i = 1; i < str.length(); i += 2) {
            int idx = str.charAt(i) - 'a';
            res *= RECORD[idx];
        }

        return res;
    }
}
