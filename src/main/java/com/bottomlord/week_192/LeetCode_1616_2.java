package com.bottomlord.week_192;

/**
 * @author chen yue
 * @date 2023-03-18 12:59:30
 */
public class LeetCode_1616_2 {
    public boolean checkPalindromeFormation(String a, String b) {
        return doCheck(a, b, 0, a.length() - 1, false) || doCheck(b, a, 0, b.length() - 1, false);
    }

    private boolean doCheck(String a, String b, int ai, int bi, boolean changed) {
        int l = ai, r = bi;
        boolean flag = true;
        while (l < r) {
            if (a.charAt(l) == b.charAt(r)) {
                l++;
                r--;
                continue;
            }

            if (changed) {
                return false;
            }

            flag = false;
            break;
        }

        if (flag) {
            return true;
        }

        return doCheck(a, a, l, r, true) || doCheck(b, b, l, r, true);
    }
}
