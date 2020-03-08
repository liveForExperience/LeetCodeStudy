package com.bottomlord.contest_20200308;

/**
 * @author ThinkPad
 * @date 2020/3/8 10:36
 */
public class Contest_1_生成每种字符都是奇数个的字符串 {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if ((n & 1) == 1) {
            for (int i = 0; i < n - 1; i++) {
                sb.append('a');
            }
            sb.append('b');
        } else {
            for (int i = 0; i < n; i++) {
                sb.append('a');
            }
        }

        return sb.toString();
    }
}
