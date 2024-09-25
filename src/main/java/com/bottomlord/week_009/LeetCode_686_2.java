package com.bottomlord.week_009;

public class LeetCode_686_2 {
    public int repeatedStringMatch(String A, String B) {
        char[] ca = A.toCharArray(), cb = B.toCharArray();
        int start = -1, ia = 0, ib = 0, ans = 0;
        while (true) {
            if (ca[ia] == cb[ib]) {
                if (start == -1) {
                    start = ia;
                }

                ib++;

                if (ib >= cb.length) {
                    return ans + 1;
                }
            } else {
                if (ans <= 1) {
                    ib = 0;

                    if (start != -1) {
                        if (start > ia) {
                            ans--;
                        }

                        ia = start;
                        start = -1;
                    }
                } else {
                    return -1;
                }
            }

            ia = (ia + 1) % ca.length;
            if (ia == 0) {
                ans++;
            }
        }
    }
}