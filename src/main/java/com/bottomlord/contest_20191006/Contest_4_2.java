package com.bottomlord.contest_20191006;

public class Contest_4_2 {
    public int countVowelPermutation(int n) {
        long a = 1, e = 1, i = 1, o = 1, u = 1, mod = 1000000007;
        for (int j = 1; j < n; j++) {
            long a1 = (e + i + u) % mod;
            long e1 = (a + i) % mod;
            long i1 = (e + o) % mod;
            long o1 = i % mod;
            long u1 = (i + o) % mod;

            a = a1; e = e1; i = i1; o = o1; u = u1;
        }

        return (int)((a + e + i + o + u) % mod);
    }
}