package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-29 18:47:54
 */
public class LeetCode_1588_2 {
    public int sumOddLengthSubarrays(int[] arr) {
        int ans = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            int leftCount = i, rightCount = n - i - 1;
            int leftOdd = (leftCount + 1) / 2,
                rightOdd = (rightCount + 1) / 2,
                leftEven = leftCount / 2 + 1,
                rightEven = rightCount / 2 + 1;

            ans += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
        }
        return ans;
    }
}
