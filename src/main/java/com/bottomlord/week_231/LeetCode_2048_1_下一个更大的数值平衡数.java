package com.bottomlord.week_231;

/**
 * @author chen yue
 * @date 2023-12-13 17:09:46
 */
public class LeetCode_2048_1_下一个更大的数值平衡数 {
    public int nextBeautifulNumber(int n) {
        while (true) {
            n++;
            if (valid(n)) {
                return n;
            }
        }
    }

    private boolean valid(int n) {
        int[] bucket = new int[10];
        while (n > 0) {
            int cur = n % 10;
            if (cur == 0) {
                return false;
            }

            bucket[cur]++;
            n /= 10;
        }

        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            if (bucket[i] != i) {
                return false;
            }
        }

        return true;
    }
}
