package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/27 22:45
 */
public class Interview_0506_1_整数转换 {
    public int convertInteger(int A, int B) {
        int xor = A ^ B, count = 0;
        while (xor != 0) {
            xor &= (xor - 1);
            count++;
        }
        return count;
    }
}
