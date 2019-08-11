package com.bottomlord.week_5;

public class LeetCode_1013_1_将数组分成和相等的三部分 {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0, avg = 0, tmp = 0, count = 0, index = 0;
        for (int num : A) {
            sum += num;
        }

        if (sum % 3 != 0) {
            return false;
        }

        avg = sum / 3;

        for (int i = 0; i < A.length; i++) {
            tmp += A[i];
            if (tmp == avg) {
                count++;
                tmp = 0;
            }

            if (count == 2) {
                index = i + 1;
                break;
            }
        }

        for (int i = index; i < A.length; i++) {
            tmp += A[i];
        }

        if (tmp == avg) {
            count++;
        }

        return count == 3;
    }
}
