package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/6 8:38
 */
public class LeetCode_1720_1_解码异或后的数组 {
    public int[] decode(int[] encoded, int first) {
        int len = encoded.length;
        int[] nums = new int[len + 1];
        nums[0] = first;
        for (int i = 1; i < len + 1; i++) {
            nums[i] = encoded[i - 1] ^ nums[i - 1];
        }
        return nums;
    }
}
