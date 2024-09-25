package com.bottomlord.contest_20191020;

public class Contest_3_替换子串得到平衡字符串 {
    public int balancedString(String s) {
        char[] cs = s.toCharArray();
        int[] bucket = new int[26];
        int l = 0, r = 0, ans = s.length(), len = s.length();
        for (char c : cs) {
            bucket[c - 'A']++;
        }

        boolean ok = true;

        for (int num : bucket) {
            if (num > len / 4) {
                ok = false;
                break;
            }
        }

        if (ok) {
            return 0;
        }

        for (; r < len; r++) {
            bucket[cs[r] - 'A']--;
            ok = true;

            for (int num : bucket) {
                if (num > len / 4) {
                    ok = false;
                    break;
                }
            }

            while (ok) {
                ans = Math.min(ans, r - l + 1);
                bucket[cs[l] - 'A']++;
                if (bucket[cs[l] - 'A'] > len / 4) {
                    ok = false;
                }
                l++;
            }
        }

        return ans;
    }
}
