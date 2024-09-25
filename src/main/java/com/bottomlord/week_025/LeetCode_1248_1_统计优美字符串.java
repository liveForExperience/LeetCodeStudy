package com.bottomlord.week_025;

/**
 * @author ThinkPad
 * @date 2019/12/29 20:44
 */
public class LeetCode_1248_1_统计优美字符串 {
    public int numberOfSubarrays(int[] nums, int k) {
        int[] arr = new int[nums.length + 2];
        arr[0] = -1;
        int index = 1, ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                arr[index++] = i;
            }
        }
        arr[index] = nums.length;

        for (int i = 1; i + k <= index; i++) {
            ans += (arr[i] - arr[i - 1]) * (arr[i + k ] - arr[i + k - 1]);
        }

        return ans;
    }
}
