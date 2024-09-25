package com.bottomlord.week_192;

/**
 * @author chen yue
 * @date 2023-03-18 12:35:09
 */
public class LeetCode_1616_1_分割两个字符串获得回文串 {
    public boolean checkPalindromeFormation(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            boolean ok = exe(a, b, i) || exe(b, a, i);
            if (ok) {
                return true;
            }
        }

        return false;
    }

    private boolean exe(String a, String b, int target) {
        int l = 0, r = a.length() - 1;
        String ls = a, rs = b;
        while (l < r) {
            if (l + 1 == target) {
                ls = b;
            }

            if (r == target) {
                rs = a;
            }

            if (ls.charAt(l++) != rs.charAt(r--)) {
               return false;
            }
        }

        return true;
    }
}
