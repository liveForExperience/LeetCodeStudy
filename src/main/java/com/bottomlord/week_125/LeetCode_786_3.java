package com.bottomlord.week_125;

/**
 * @author chen yue
 * @date 2021-11-29 22:16:17
 */
public class LeetCode_786_3 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        double head = 0, tail = 1;
        while (true) {
            double mid = (head + tail) / 2;
            int count = 0, x = 0, y = 1;
            for (int j = 1; j < arr.length; j++) {
                int i = -1;
                while (arr[i + 1] * 1.0 / arr[j] < mid) {
                    i++;
                    if (arr[i] * 1.0 / arr[j] > x * 1.0 / y) {
                        x = arr[i];
                        y = arr[j];
                    }
                }
                count += i + 1;
            }

            if (count == k) {
                return new int[]{x, y};
            }

            if (count > k) {
                tail = mid;
            } else {
                head = mid;
            }
        }
    }
}