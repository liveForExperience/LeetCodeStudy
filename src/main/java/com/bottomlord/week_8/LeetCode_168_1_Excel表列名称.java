package com.bottomlord.week_8;

public class LeetCode_168_1_Excel表列名称 {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int mod = n % 26;
            n /= 26;
            if (mod == 0) {
                sb.insert(0, 'Z');
                n--;
            } else {
                sb.insert(0, (char)(mod - 1 + 'A'));
            }
        }
        return sb.toString();
    }
}
