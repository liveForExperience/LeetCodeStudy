package com.bottomlord.week_106;

/**
 * @author ChenYue
 * @date 2021/7/23 8:46
 */
public class LeetCode_1385_1_两个数组间的距离值 {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        for (int num1 : arr1) {
            boolean miss = false;
            int left = num1 - d, right = num1 + d;
            for (int num2 : arr2) {
                if (num2 >= left && num2 <= right) {
                    miss = true;
                    break;
                }
            }

            count += miss ? 0 : 1;
        }

        return count;
    }
}
