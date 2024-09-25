package com.bottomlord.week_068;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/10/23 11:57
 */
public class LeetCode_316_1_去除重复字母 {
    public String removeDuplicateLetters(String s) {
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length; i++) {
            if (i == cs.length - 1) {
                sb.append(cs[i]);
                break;
            }

            if (cs[i] == cs[i + 1]) {
                continue;
            }

            sb.append(cs[i]);
        }
        return sb.toString();
    }
}
