package com.bottomlord.week_132;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-01-18 21:29:28
 */
public class LeetCode_2094_2 {
    public int[] findEvenNumbers(int[] digits) {
        int[] bucket = new int[10];
        for (int digit : digits) {
            bucket[digit]++;
        }

        int[] ans = new int[8];
        int index = 0;

        for (int i = 1; i < 10; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            bucket[i]--;
            for (int j = 0; j < 10; j++) {
                if (bucket[j] == 0) {
                    continue;
                }

                bucket[j]--;

                for (int k = 0; k < 10; k += 2) {
                    if (bucket[k] == 0) {
                        continue;
                    }

                    int num = i * 100 + j * 10 + k;
                    if (index >= ans.length) {
                        ans = Arrays.copyOf(ans, index << 1);
                    }
                    ans[index++] = num;
                }

                bucket[j]++;
            }

            bucket[i]++;
        }

        return Arrays.copyOf(ans, index);
    }
}
