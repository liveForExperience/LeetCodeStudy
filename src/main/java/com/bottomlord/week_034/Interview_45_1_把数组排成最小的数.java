package com.bottomlord.week_034;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/1 19:32
 */
public class Interview_45_1_把数组排成最小的数 {
    public String minNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        StringBuilder sb = new StringBuilder();
        for (String str : arr) {
            sb.append(str);
        }
        return sb.toString();
    }
}
