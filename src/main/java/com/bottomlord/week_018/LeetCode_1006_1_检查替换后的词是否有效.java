package com.bottomlord.week_018;

import java.util.Objects;

public class LeetCode_1006_1_检查替换后的词是否有效 {
    public boolean isValid(String S) {
        while (S.contains("abc")) {
            S = S.replaceAll("abc", "");
        }
        return Objects.equals(S, "");
    }
}
