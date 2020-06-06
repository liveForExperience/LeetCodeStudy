package com.bottomlord.week_048;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2020/6/6 20:57
 */
public class LeetCode_128_1_最长连续序列 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet()),
                     memo = new HashSet<>();
        int max = 0;
        for (int num : set) {
            if (memo.contains(num)) {
                continue;
            }

            memo.add(num);
            int len = 1;
            int add = num + 1;
            while (set.contains(add)) {
                memo.add(add++);
                len++;
            }

            int minus = num - 1;
            while (set.contains(minus)) {
                memo.add(minus--);
                len++;
            }

            max = Math.max(max, len);
        }

        return max;
    }
}
