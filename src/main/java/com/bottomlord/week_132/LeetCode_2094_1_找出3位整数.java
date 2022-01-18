package com.bottomlord.week_132;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-01-18 21:06:59
 */
public class LeetCode_2094_1_找出3位整数 {
    public int[] findEvenNumbers(int[] digits) {
        Arrays.sort(digits);
        int n = digits.length;
        Set<Integer> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }

                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int num = 100 * digits[i] + 10 * digits[j] + digits[k];
                    if (set.add(num)) {
                        ans.add(num);
                    }
                }
            }
        }

        return ans.stream().mapToInt(x -> x).toArray();
    }
}
