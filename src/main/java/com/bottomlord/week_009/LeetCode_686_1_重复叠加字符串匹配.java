package com.bottomlord.week_009;

public class LeetCode_686_1_重复叠加字符串匹配 {
    public int repeatedStringMatch(String A, String B) {
        if (A.contains(B)) {
            return 1;
        }

        boolean has = false;
        for (int j = 0; j < A.length(); j++) {
            int ai = j;
            boolean find = true;
            for (int i = 0; i < B.length(); i++) {
                if (A.charAt(ai) != B.charAt(i)) {
                    find = false;
                    break;
                }

                if (++ai == A.length()) {
                    ai = 0;
                }
            }

            if (find) {
                has = true;
                break;
            }
        }

        if (!has) {
            return -1;
        }

        int count = 1;
        StringBuilder sb = new StringBuilder(A);
        while (!sb.toString().contains(B)) {
            sb.append(A);
            count++;
        }
        return count;
    }
}
