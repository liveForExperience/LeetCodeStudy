package com.bottomlord.week_187;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-02-08 11:32:43
 */
public class LeetCode_2553_1_分割数组中数字的数位 {
    public int[] separateDigits(int[] nums) {
        List<Integer> all = new LinkedList<>();
        for (int num : nums) {
            List<Integer> list = new ArrayList<>();
            while (num > 0) {
                list.add(num % 10);
                num /= 10;
            }

            reverse(list);
            all.addAll(list);
        }

        int[] ans = new int[all.size()];
        for (int i = 0; i < all.size(); i++) {
            ans[i] = all.get(i);
        }
        return ans;
    }

    private void reverse(List<Integer> list) {
        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            int tmp = list.get(tail);
            list.set(tail, list.get(head));
            list.set(head, tmp);

            head++;
            tail--;
        }
    }
}
