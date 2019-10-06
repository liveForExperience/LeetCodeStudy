package com.bottomlord.contest_20191005;

import java.util.*;

public class Contest_3_2 {
    private Set<Long> set = new HashSet<>();

    public List<Integer> countSteppingNumbers(int low, int high) {
        set.add(0L);
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            recurse(i);
        }

        for (long num : set) {
            if (num >= low && num <= high) {
                ans.add((int)num);
            }
        }

        Collections.sort(ans);
        return ans;
    }

    private void recurse(long num) {
        if (num > Integer.MAX_VALUE) {
            return;
        }
        set.add(num);
        long cur = num % 10;
        if (cur > 0) {
            recurse(num * 10 + cur - 1);
        }

        if (cur < 9) {
            recurse(num * 10 + cur + 1);
        }
    }
}
