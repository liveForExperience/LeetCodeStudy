package com.bottomlord.week_187;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-02-08 11:32:43
 */
public class LeetCode_2553_2 {
    public int[] separateDigits(int[] nums) {
        List<Integer> all = new LinkedList<>();
        for (int num : nums) {
            LinkedList<Integer> list = new LinkedList<>();
            while (num > 0) {
                list.addFirst(num % 10);
                num /= 10;
            }

            all.addAll(list);
        }

        int[] ans = new int[all.size()];
        for (int i = 0; i < all.size(); i++) {
            ans[i] = all.get(i);
        }
        return ans;
    }
}
