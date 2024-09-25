package com.bottomlord.week_129;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-12-30 20:15:40
 */
public class LeetCode_1961_1_检查字符串是否为数组前缀 {
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (Objects.equals(sb.toString(), s)) {
                return true;
            }
        }

        return false;
    }
}
