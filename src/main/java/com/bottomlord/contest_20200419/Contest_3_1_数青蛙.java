package com.bottomlord.contest_20200419;

/**
 * @author ChenYue
 * @date 2020/4/19 11:15
 */
public class Contest_3_1_数青蛙 {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, ans = 0;
        for (char ch : croakOfFrogs.toCharArray()) {
            if (ch == 'c') {
                c++;
            } else if (ch == 'r') {
                r++;
            } else if (ch == 'o') {
                o++;
            } else if (ch == 'a') {
                a++;
            } else if (ch == 'k') {
                k++;
            }

            ans = Math.max(ans, c);
            if (ch == 'k') {
                if (c >= r && r >= o && o >= a && a >= k) {
                    c--;
                    r--;
                    o--;
                    a--;
                    k--;
                }
            }

            if (!(c >= r && r >= o && o >= a && a >= k)) {
                return -1;
            }
        }

        if (c != 0 || r != 0 || o != 0 || a != 0 || k != 0) {
            return -1;
        }

        return ans;
    }
}
