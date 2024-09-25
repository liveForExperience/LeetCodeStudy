package com.bottomlord.week_104;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1239_1_串联字符串的最大长度 {
    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i) - 'a';
                if (((mask >> ch) & 1) != 0) {
                    mask = 0;
                    break;
                }

                mask |= 1 << ch;
            }

            if (mask > 0) {
                masks.add(mask);
            }
        }

        return backTrack(masks, 0, 0);
    }

    private int backTrack(List<Integer> masks, int pos, int mask) {
        if (pos == masks.size()) {
            return Integer.bitCount(mask);
        }

        int ans = 0;

        if ((mask & masks.get(pos)) == 0) {
            ans = Math.max(ans, backTrack(masks, pos + 1, mask | masks.get(pos)));
        }

        return Math.max(ans, backTrack(masks, pos + 1, mask));
    }
}
