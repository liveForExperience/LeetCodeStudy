package com.bottomlord.week_036;

import java.util.Objects;

/**
 * @author ThinkPad
 * @date 2020/3/14 14:16
 */
public class Interview_0103_1_URL化 {
    public String replaceSpaces(String S, int length) {
        String[] ans = new String[length], splits = S.split("");
        for (int i = 0; i < length; i++) {
            if (Objects.equals(splits[i], " ")) {
                ans[i] = "%20";
            } else {
                ans[i] = splits[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String str : ans) {
            sb.append(str);
        }

        return sb.toString();
    }
}
