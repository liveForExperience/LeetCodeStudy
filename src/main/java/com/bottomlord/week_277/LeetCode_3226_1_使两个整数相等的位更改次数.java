package com.bottomlord.week_277;

/**
 * @author chen yue
 * @date 2024-11-02 14:33:29
 */
public class LeetCode_3226_1_使两个整数相等的位更改次数 {
    public int minChanges(int n, int k) {
        int cnt = 0;
        do {
            if ((n & 1) == (k & 1)) {
                continue;
            }

            if ((n & 1) == 0) {
                return -1;
            }

            cnt++;
        } while ((n = n >> 1) > 0 | (k = k >> 1) > 0);

        return cnt;
    }

    public static void main(String[] args) {
        LeetCode_3226_1_使两个整数相等的位更改次数 t = new LeetCode_3226_1_使两个整数相等的位更改次数();
        t.minChanges(13, 4);
    }
}
