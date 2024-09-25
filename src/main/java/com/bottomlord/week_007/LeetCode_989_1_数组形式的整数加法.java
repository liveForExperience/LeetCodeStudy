package com.bottomlord.week_007;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_989_1_数组形式的整数加法 {
    public List<Integer> addToArrayForm(int[] A, int K) {
        char[] kcs = Integer.toString(K).toCharArray();
        int kLen = kcs.length, aLen = A.length, ki = kLen - 1, ai = aLen - 1, carry = 0;
        List<Integer> ans = new ArrayList<>();
        while (ki >= 0 && ai >= 0) {
            int k = Integer.parseInt(Character.toString(kcs[ki--]));
            int a = A[ai--];
            int sum = k + a + carry;
            carry = sum >= 10 ? sum / 10 : 0;
            ans.add(0, sum % 10);
        }

        while (ki >= 0) {
            int sum = Integer.parseInt(Character.toString(kcs[ki--])) + carry;
            carry = sum >= 10 ? sum / 10 : 0;
            ans.add(0, sum % 10);
        }

        while (ai >= 0){
            int sum = A[ai--] + carry;
            carry = sum >= 10 ? sum / 10 : 0;
            ans.add(0, sum % 10);
        }

        if (carry != 0) {
            ans.add(0, carry);
        }

        return ans;
    }
}
