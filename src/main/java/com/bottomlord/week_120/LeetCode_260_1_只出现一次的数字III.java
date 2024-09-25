package com.bottomlord.week_120;

/**
 * @author chen yue
 * @date 2021-10-30 14:23:42
 */
public class LeetCode_260_1_只出现一次的数字III {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        int lsb = xor == Integer.MIN_VALUE ? xor : xor & (-xor);
        int one = 0, two = 0;
        for (int num : nums) {
            if ((lsb & num) == 0) {
                one ^= num;
            } else {
                two ^= num;
            }
        }

        return new int[]{one, two};
    }
}
