package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-22 08:51:11
 */
public class LeetCode_686_1_重复叠加字符串匹配 {
    public int repeatedStringMatch(String a, String b) {
        if (a.contains(b)) {
            return 1;
        }

        boolean is = false;
        for (int i = 0; i < a.length(); i++) {
            boolean match = true;
            int ia = i;
            for (int ib = 0; ib < b.length(); ib++) {
                if (a.charAt(ia) != b.charAt(ib)) {
                    match = false;
                    break;
                }

                if (++ia == a.length()) {
                    ia = 0;
                }
            }

            if (match) {
                is = true;
                break;
            }
        }

        if (!is) {
            return -1;
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (true) {
            if (sb.toString().contains(b)) {
                return count;
            }

            sb.append(a);
            count++;
        }
    }
}
