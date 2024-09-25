package com.bottomlord.week_007;

public class LeetCode_66_1_加一 {
    public int[] plusOne(int[] digits) {
        int[] ans = new int[digits.length + 1];
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            ans[i + 1] = sum % 10;
            carry = sum >= 10 ? 1 : 0;
        }

        if (carry == 1) {
            ans[0] = 1;
            return ans;
        } else {
            int[] newAns = new int[ans.length - 1];
            System.arraycopy(ans, 1, newAns, 0, ans.length - 1);
            return newAns;
        }
    }
}
