package com.bottomlord.week_106;

public class LeetCode_1399_1_统计最大组的数目 {
    public int countLargestGroup(int n) {
        int[] arr = new int[37];
        for (int i = 1; i <= n; i++) {
            arr[cal(i)]++;
        }

        int max = 0, count = 0;
        for (int num : arr) {
            if (num == 0) {
                continue;
            }

            if (num > max) {
                max = num;
                count = 1;
            } else if (num == max) {
                count++;
            }
        }

        return count;
    }

    private int cal(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
