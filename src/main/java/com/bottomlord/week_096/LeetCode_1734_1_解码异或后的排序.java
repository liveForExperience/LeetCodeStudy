package com.bottomlord.week_096;

public class LeetCode_1734_1_解码异或后的排序 {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] ans = new int[n];

        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }

        int xor = 0;
        for (int i = 1; i < n - 1; i += 2) {
            xor ^= encoded[i];
        }

        int first = total ^ xor;

        ans[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }

        return ans;
    }
}
