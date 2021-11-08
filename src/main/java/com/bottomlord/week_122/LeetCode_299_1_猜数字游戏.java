package com.bottomlord.week_122;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-11-08 08:49:40
 */
public class LeetCode_299_1_猜数字游戏 {
    public String getHint(String secret, String guess) {
        int n = secret.length(), a = 0, b = 0;
        Set<Integer> set = new HashSet<>();
        int[] bucket = new int[10];
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                set.add(i);
                a++;
                continue;
            }

            bucket[secret.charAt(i) - '0']++;
        }

        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }

            bucket[guess.charAt(i) - '0']--;
        }

        int[] arr = Arrays.copyOf(bucket, bucket.length);
        for (int i = 0; i < bucket.length; i++) {
            b += arr[i] - Math.max(bucket[i], 0);
        }

        return a + "A" + b + "B";
    }
}
