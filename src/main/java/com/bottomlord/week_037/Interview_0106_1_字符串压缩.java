package com.bottomlord.week_037;

/**
 * @author ThinkPad
 * @date 2020/3/16 8:35
 */
public class Interview_0106_1_字符串压缩 {
    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length();) {
            char start = S.charAt(i);
            int j = i + 1;
            while (j < S.length() && start == S.charAt(j)) {
                j++;
            }
            sb.append(start).append(j - i);
            i = j;
        }
        return sb.length() >= S.length() ? S : sb.toString();
    }
}
