package com.bottomlord.week_009;

public class LeetCode_949_2 {
    public String largestTimeFromDigits(int[] A) {
        int ans = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    for (int k = 0; k < 4; k++) {
                        if (i != k && j != k) {
                            int l = 6 - i - j - k;

                            int hour = A[i] * 10 + A[j];
                            int minute = A[k] * 10 + A[l];

                            if (hour < 24 && minute < 60) {
                                ans = Math.max(ans, hour * 60 + minute);
                            }
                        }
                    }
                }
            }
        }

        return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60): "";
    }
}