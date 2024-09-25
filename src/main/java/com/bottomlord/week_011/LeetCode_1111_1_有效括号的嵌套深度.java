package com.bottomlord.week_011;

public class LeetCode_1111_1_有效括号的嵌套深度 {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int flag = 1;
        for (int i = 0; i < seq.length(); i++) {
            if ('(' == seq.charAt(i)) {
                flag ^= 1;
                ans[i] = flag;
            } else {
                ans[i] = flag;
                flag ^= 1;
            }
        }
        return ans;
    }
}
