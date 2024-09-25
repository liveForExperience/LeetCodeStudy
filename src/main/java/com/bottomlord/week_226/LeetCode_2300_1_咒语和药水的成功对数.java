package com.bottomlord.week_226;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-11-10 09:54:15
 */
public class LeetCode_2300_1_咒语和药水的成功对数 {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length, n = potions.length;
        int[] ans = new int[m];
        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            ans[i] = n - binarySearch(potions, spells[i], success);
        }

        return ans;
    }

    private int binarySearch(int[] arr, int x, long target) {
        int head = 0, tail = arr.length - 1;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            long cur = (long) arr[mid] * x;

            if (cur < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return head;
    }
}
