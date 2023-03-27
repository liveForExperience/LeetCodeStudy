package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-03-27 21:19:53
 */
public class LeetCode_1638_1_统计只差一个字符的子串数目 {
    public int countSubstrings(String s, String t) {
        int sn = s.length(), tn = t.length(), ans = 0;
        for (int i = 0; i < sn; i++) {
            for (int j = 0; j < tn; j++) {
                int cnt = 0;
                int offset = 0;
                while (cnt < 2) {
                    char sc = s.charAt(i + offset), tc = t.charAt(j + offset);
                    cnt += sc == tc ? 0 : 1;
                    if (cnt == 1) {
                        ans++;
                    }
                    offset++;
                }
            }
        }

        return ans;
    }
}
