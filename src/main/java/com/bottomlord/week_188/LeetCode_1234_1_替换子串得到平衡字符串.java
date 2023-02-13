package com.bottomlord.week_188;

/**
 * @author chen yue
 * @date 2023-02-13 17:36:27
 */
public class LeetCode_1234_1_替换子串得到平衡字符串 {
    public int balancedString(String s) {
        int l = 0, r = 0, n = s.length(), ans = n;
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'A']++;
        }

        boolean ok = true;

        for (int num : bucket) {
            if (num > n / 4) {
                ok = false;
                break;
            }
        }

        if (ok) {
            return 0;
        }

        for (; r < s.length(); r++) {
            bucket[s.charAt(r) - 'A']--;
            ok = true;

            for (int num : bucket) {
                if (num > n / 4) {
                    ok = false;
                    break;
                }
            }

            while (ok) {
                ans = Math.min(ans, r - l + 1);
                bucket[s.charAt(l) - 'A']++;

                if (bucket[s.charAt(l) - 'A'] > n / 4) {
                    ok = false;
                }

                l++;
            }
        }

        return ans;
    }
}
