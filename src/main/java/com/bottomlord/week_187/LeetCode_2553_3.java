package com.bottomlord.week_187;

import java.util.LinkedList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-02-08 11:32:43
 */
public class LeetCode_2553_3 {
    public int[] separateDigits(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }

        char[] cs = sb.toString().toCharArray();
        int[] ans = new int[cs.length];
        for (int i = 0; i < cs.length; i++) {
            ans[i] = cs[i] - '0';
        }
        return ans;
    }
}
