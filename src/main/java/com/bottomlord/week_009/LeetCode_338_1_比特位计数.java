package com.bottomlord.week_009;

public class LeetCode_338_1_比特位计数 {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int tmp = i, count = 0;
            while (tmp > 0) {
                tmp &= tmp - 1;
                count++;
            }
            ans[i] = count;
        }
        return ans;
    }
}
