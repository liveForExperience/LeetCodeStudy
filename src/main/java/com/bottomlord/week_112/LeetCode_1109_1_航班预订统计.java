package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-08-31 08:25:27
 */
public class LeetCode_1109_1_航班预订统计 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        for (int[] booking : bookings) {
            nums[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                nums[booking[1]] -= booking[2];
            }
        }

        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }

        return nums;
    }
}
