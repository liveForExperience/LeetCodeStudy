package com.bottomlord.week_189;

/**
 * @author chen yue
 * @date 2023-02-26 13:48:33
 */
public class LeetCode_779_1_第K个语法符号 {
    public int kthGrammar(int n, int k) {
        if (n == 1 || k == 1) {
            return 0;
        }

        int mid = (int) Math.pow(2, n - 2);
        if (k <= mid) {
            return kthGrammar(n - 1, k);
        }

        return kthGrammar(n - 1, k - mid) ^ 1;
    }
}
