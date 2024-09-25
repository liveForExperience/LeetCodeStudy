package com.bottomlord.week_137;

/**
 * @author chen yue
 * @date 2022-02-22 10:03:47
 */
public class LeetCode_1994_1_好子集的数目 {
    private final int[] p = new int[]{2,3,5,7,11,13,17,19,23,29};
    public int numberOfGoodSubsets(int[] nums) {
        int[] buckets = new int[35];
        for (int i : nums) {
            buckets[i]++;
        }

        int mask = 1 << 10;
        long[] states = new long[mask];
        states[0] = 1;

        int mod = (int) 1e9 + 7;
        for (int i = 2; i <= 30; i++) {
            if (buckets[i] == 0) {
                continue;
            }

            int cur = 0, x = i;
            boolean ok = true;
            for (int j = 0; j < 10 && ok; j++) {
                int c = 0;
                while (x % p[j] == 0) {
                    cur |= (1 << j);
                    c++;
                    x /= p[j];
                }

                if (c > 1) {
                    ok = false;
                }
            }

            if (!ok) {
                continue;
            }

            for (int pre = mask - 1; pre >= 0; pre--) {
                if ((cur & pre) != 0) {
                    continue;
                }

                states[pre | cur] = (states[pre | cur] + states[pre] * buckets[i]) % mod;
            }
        }

        long ans = 0;
        for (int i = 1; i < mask; i++) {
            ans = (ans + states[i]) % mod;
        }

        for (int i = 0; i < buckets[1]; i++) {
            ans = (ans * 2) % mod;
        }

        return (int)ans;
    }
}
