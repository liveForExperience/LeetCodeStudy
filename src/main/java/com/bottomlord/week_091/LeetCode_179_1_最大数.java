package com.bottomlord.week_091;

import java.util.Arrays;

public class LeetCode_179_1_最大数 {
    public String largestNumber(int[] nums) {
        String[] numStrs = Arrays.stream(nums).boxed().map(String::valueOf).toArray(String[]::new);
        Arrays.sort(numStrs, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        String ans = String.join("", numStrs);
        return ans.charAt(0) == '0' ? "0" : ans;
    }
}
