package com.bottomlord.week_062;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/9/13 18:19
 */
public class LeetCode_267_1_回文排列II {
    public List<String> generatePalindromes(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }

        int[] bucket = new int[128];
        if (!canPermutePalindromes(s, bucket)) {
            return new ArrayList<>();
        }

        char c = 0;
        int index = 0;
        char[] cs = new char[s.length() / 2];
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] % 2 == 1) {
                c = (char) i;
            }

            for (int j = 0; j < bucket[i] / 2; j++) {
                cs[index++] = (char) i;
            }
        }

        Set<String> ans = new HashSet<>();
        permute(ans, 0, cs, c);
        return new ArrayList<>(ans);
    }

    private boolean canPermutePalindromes(String s, int[] bucket) {
        int count = 0;
        for (char c : s.toCharArray()) {
            bucket[c]++;
            if (bucket[c] % 2 == 0) {
                count--;
            } else {
                count++;
            }
        }

        return count <= 1;
    }

    private void swap(char[] cs, int x, int y) {
        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }

    private void permute(Set<String> ans, int index, char[] cs, char c) {
        if (index == cs.length) {
            ans.add(new String(cs) + (c == 0 ? "" : c) + new StringBuilder(new String(cs)).reverse());
            return;
        }

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] != cs[index] || i == index) {
                swap(cs, i, index);
                permute(ans, index + 1, cs, c);
                swap(cs, i, index);
            }
        }
    }
}
