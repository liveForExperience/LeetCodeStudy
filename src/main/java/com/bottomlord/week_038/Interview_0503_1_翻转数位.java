package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/27 8:28
 */
public class Interview_0503_1_翻转数位 {
    public int reverseBits(int num) {
        int[] bits = new int[32];
        int t = 1;
        for (int i = 0; i < 32; i++) {
            if ((num & (t << i)) != 0) {
                bits[i] = 1;
            } else {
                bits[i] = 0;
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0, life = 1;
            for (int j = i; j < 32; j++) {
                if (bits[j] == 1) {
                    count++;
                } else if (life == 1) {
                    count++;
                    life--;
                } else {
                    break;
                }
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }
}
