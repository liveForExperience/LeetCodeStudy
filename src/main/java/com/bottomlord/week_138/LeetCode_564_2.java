package com.bottomlord.week_138;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-03 09:22:11
 */
public class LeetCode_564_2 {
    public String nearestPalindromic(String n) {
        long target = Long.parseLong(n), ans = -1;
        List<Long> candidates = getCandidates(n);
        for (Long candidate : candidates) {
            if (target == candidate) {
                continue;
            }

            if (ans == -1 ||
                    Math.abs(candidate - target) < Math.abs(ans - target) ||
                    (Math.abs(candidate - target) == Math.abs(ans - target) && candidate < target)) {
                ans = candidate;
            }
        }

        return String.valueOf(ans);
    }

    private List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<>();
        candidates.add((long) Math.pow(10, len - 1) - 1);
        candidates.add((long) Math.pow(10, len) + 1);

        long prefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long num = prefix - 1; num <= prefix + 1; num++) {
            StringBuilder sb = new StringBuilder();
            String candidatePrefix = String.valueOf(num);
            sb.append(candidatePrefix);
            StringBuilder suffixSb = new StringBuilder(candidatePrefix).reverse();
            String suffix = suffixSb.substring(len & 1);
            sb.append(suffix);
            candidates.add(Long.parseLong(sb.toString()));
        }

        return candidates;
    }
}