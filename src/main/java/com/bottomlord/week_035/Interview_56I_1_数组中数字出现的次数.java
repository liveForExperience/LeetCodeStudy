package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/6 12:19
 */
public class Interview_56I_1_数组中数字出现的次数 {
    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        int bit = 1;
        while ((bit & xor) == 0) {
            bit <<= 1;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            if ((bit & num) == 1) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};
    }
}
