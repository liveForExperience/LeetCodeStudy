package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/3/30 8:31
 */
public class Interview_0803_1_魔术索引 {
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                return i;
            }
        }

        return -1;
    }
}
