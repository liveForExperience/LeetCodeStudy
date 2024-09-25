package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-22 09:10:47
 */
public class LeetCode_686_2 {
    public int repeatedStringMatch(String a, String b) {
        boolean[] bucket = new boolean[26];
        char[] as = a.toCharArray(), bs = b.toCharArray();
        for (char c : as) {
            bucket[c - 'a'] = true;
        }

        for (char c : bs) {
            if (!bucket[c - 'a']) {
                return -1;
            }
        }

        int x = b.length() / a.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append(a);
        }

        for (int i = 0; i <= 2; i++) {
            if (sb.indexOf(b) >= 0) {
                return x + i;
            }

            sb.append(a);
        }

        return -1;
    }
}