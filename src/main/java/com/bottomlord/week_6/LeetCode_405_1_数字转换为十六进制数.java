package com.bottomlord.week_6;

public class LeetCode_405_1_数字转换为十六进制数 {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        char[] dict = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.insert(0, dict[num & 15]);
            num >>>= 4;
        }

        return sb.toString();
    }
}
