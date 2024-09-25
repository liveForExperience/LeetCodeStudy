package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-21 17:52:15
 */
public class LeetCode_1945_1_字符串转化后的各位数字之和 {
    public int getLucky(String s, int k) {
        String numStr = str2NumStr(s);
        int num = 0;
        for (int i = 0; i < k; i++) {
            num = str2Num(numStr);
            numStr = Integer.toString(num);
        }
        return num;
    }

    private String str2NumStr(String str) {
        char[] cs = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            sb.append(c - 'a' + 1);
        }

        return sb.toString();
    }

    private int str2Num(String str) {
        char[] cs = str.toCharArray();
        int sum = 0;
        for (char c : cs) {
            sum += c - '0';
        }
        return sum;
    }
}
