package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/6 21:58
 */
public class Interview_1701_1_不用加号的加法 {
    public int add(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = (a ^ b);
            b = carry;
        }

        return a;
    }
}
