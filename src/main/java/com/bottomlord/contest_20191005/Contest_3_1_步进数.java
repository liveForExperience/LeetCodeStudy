package com.bottomlord.contest_20191005;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contest_3_1_步进数 {
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> mem = new HashSet<>();
        for (int i = low; i <= high; i++) {
            if (i <= 10 || isValid(i, mem)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean isValid(int num, Set<Integer> set) {
        int tmp = num;

        int pre = num % 10;
        num /= 10;

        while (num > 0) {
            if (set.contains(num)) {
                return true;
            }

            int cur = num % 10;
            if (Math.abs(pre - cur) != 1) {
                return false;
            }
            pre = cur;
            num /= 10;
        }

        set.add(tmp);
        return true;
    }
}
