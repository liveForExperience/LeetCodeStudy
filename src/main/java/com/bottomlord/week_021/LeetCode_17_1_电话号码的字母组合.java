package com.bottomlord.week_021;

import java.util.*;

public class LeetCode_17_1_电话号码的字母组合 {
    private Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (Objects.equals(digits, "")) {
            return ans;
        }
        backTrack("", digits, ans);
        return ans;
    }

    private void backTrack(String str, String digits, List<String> ans) {
        if (digits.length() == 0) {
            ans.add(str);
            return;
        }

        String digit = digits.substring(0, 1);
        String letters = phone.get(digit);

        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.substring(i, i + 1);
            backTrack(str + letter, digits.substring(1), ans);
        }
    }
}
