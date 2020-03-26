package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/26 14:57
 */
public class Interview_0501_1_插入 {
    public int insertBits(int N, int M, int i, int j) {
        int t = 0;
        for (int k = i; k <= j; k++) {
            t |= (1 << k);
        }

        N &= ~t;
        return N | (M << i);
    }
}
