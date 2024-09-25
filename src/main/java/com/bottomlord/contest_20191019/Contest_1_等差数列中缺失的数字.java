package com.bottomlord.contest_20191019;

public class Contest_1_等差数列中缺失的数字 {
    public int missingNumber(int[] arr) {
        int sum = (arr[0] + arr[arr.length - 1]) * (arr.length + 1) / 2;
        int count = 0;
        for (int num : arr) {
            count += num;
        }
        return sum - count;
    }
}
