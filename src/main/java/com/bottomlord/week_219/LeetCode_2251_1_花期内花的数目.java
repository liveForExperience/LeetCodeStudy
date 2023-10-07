package com.bottomlord.week_219;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-09-28 16:15:00
 */
public class LeetCode_2251_1_花期内花的数目 {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int[] starts = new int[n], ends = new int[n];
        for (int i = 0; i < flowers.length; i++) {
            int[] flower = flowers[i];
            starts[i] = flower[0];
            ends[i] = flower[1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int[] ans = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            int person = people[i];
            int bloom = binarySearchBiggestLowerOrEquals(starts, person) + 1,
                fade = binarySearchBiggestLower(ends, person);
            ans[i] = bloom - fade;
        }
        return ans;
    }

    private int binarySearchBiggestLowerOrEquals(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        while (head + 1 < tail) {
            int mid = head + (tail - head) / 2;

            if (arr[mid] <= target) {
                head = mid;
            } else {
                tail = mid;
            }
        }

        return arr[head] > target ? -1 : arr[tail] <= target ? tail : head;
    }

    private int binarySearchBiggestLower(int[] arr, int target) {
        int head = 0, tail = arr.length - 1;
        while (head + 1 < tail) {
            int mid = head + (tail - head) / 2;

            if (arr[mid] < target) {
                head = mid;
            } else {
                tail = mid;
            }
        }

        return arr[head] >= target ? -1 : head;
    }
}
