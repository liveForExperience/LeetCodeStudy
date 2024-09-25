package com.bottomlord.week_169;

/**
 * @author chen yue
 * @date 2022-10-06 12:35:46
 */
public class LeetCode_927_1_三等分 {
    public int[] threeEqualParts(int[] arr) {
        int count = 0, n = arr.length;
        for (int num : arr) {
            count += num;
        }

        if (count == 0) {
            return new int[]{0, n - 1};
        }

        if (count % 3 != 0) {
            return new int[]{-1, -1};
        }

        int len = count / 3, first = -1,
                second = -1, third = -1,
                cur = 0;

        for (int i = 0; i < n; i++) {
            int num = arr[i];

            if (num == 0) {
                continue;
            }

            if (cur == 0) {
                first = i;
            } else if (cur == len) {
                second = i;
            } else if (cur == 2 * len) {
                third = i;
            }

            cur++;
        }

        int suffix = n - third;

        if (second - first < suffix ||
            third - second < suffix) {
            return new int[]{-1, -1};
        }

        for (int i = first, j = second, k = third; k < n; i++, j++, k++) {
            if (arr[i] != arr[j] ||
                arr[j] != arr[k]) {
                return new int[]{-1, -1};
            }
        }

        return new int[]{first + suffix, second + suffix};
    }
}
