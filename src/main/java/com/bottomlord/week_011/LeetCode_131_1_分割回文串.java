package com.bottomlord.week_011;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_131_1_分割回文串 {
    public List<List<String>> partition(String s) {
        return recurse(s, 0);
    }

    private List<List<String>> recurse(String s, int start) {
        List<List<String>> ans = new ArrayList<>();
        if (start == s.length()) {
            List<String> list = new ArrayList<>();
            ans.add(list);
            return ans;
        }

        for (int i = start; i < s.length(); i++) {
            String left = s.substring(start, i + 1);
            if (ispalindrome(left)) {
                for (List<String> list : recurse(s, start + 1)) {
                    list.add(0, left);
                    ans.add(list);
                }
            }
        }

        return ans;
    }

    private boolean ispalindrome(String s) {
        int head = 0, tail = s.length() - 1;
        while (head <= tail) {
            if (s.charAt(head) != s.charAt(tail)) {
                return false;
            }

            head++;
            tail--;
        }
        return true;
    }
}
