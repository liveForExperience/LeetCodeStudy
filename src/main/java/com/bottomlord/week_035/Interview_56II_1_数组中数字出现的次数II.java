package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/7 14:24
 */
public class Interview_56II_1_数组中数字出现的次数II {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int bit = 1 << i, count = 0;
            for (int num : nums) {
                if ((num & bit) != 0) {
                    count++;
                }
            }

            if (count % 3 != 0) {
                ans |= bit;
            }
        }

        return ans;
    }
}
