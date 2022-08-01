package com.bottomlord.week_155;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-07-04 21:54:39
 */
public class LeetCode_556_1_下一个更大元素III {
    public int nextGreaterElement(int n) {
        List<Integer> list = new ArrayList<>();

        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }

        int index = -1;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1) < list.get(i)) {
                index = i + 1;
                break;
            }
        }

        if (index == -1) {
            return -1;
        }

        for (int i = 0; i < index; i++) {
            if (list.get(i) > list.get(index)) {
                swap(list, i, index);
                break;
            }
        }

        int l = 0, r = index - 1;
        while (l < r) {
            swap(list, l++, r--);
        }

        long ans = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int num = list.get(i);
            ans = ans * 10 + num;
        }

        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    private void swap(List<Integer> list, int l, int r) {
        int tmp = list.get(l);
        list.set(l, list.get(r));
        list.set(r, tmp);
    }
}
