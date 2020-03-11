package com.bottomlord.week_005;

/**
 * @author ThinkPad
 * @date 2020/3/11 8:33
 */
public class LeetCode_1013_2 {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int num : A) {
            sum += num;
        }

        if (sum % 3 != 0) {
            return false;
        }

        int left = 0, right = A.length - 1, leftSum = A[left], rightSum = A[A.length - 1];
        while (leftSum != sum / 3 && left + 1 < right) {
            leftSum += A[++left];
        }
        if (left + 1 >= right) {
            return false;
        }

        while (rightSum != sum / 3 && left + 1 < right) {
            rightSum += A[--right];
        }

        return left + 1 < right;
    }
}
