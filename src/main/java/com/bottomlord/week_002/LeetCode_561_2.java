package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/16 20:46
 */
public class LeetCode_561_2 {
    public int arrayPairSum(int[] nums) {
        int[] bucket = new int[20001];
        for (int num: nums) {
            bucket[num + 10000]++;
        }

        int sum = 0;
        boolean flag = true;

        for (int i = 0; i < 20001; i++) {
            while (bucket[i] > 0) {
                if (flag) {
                    sum += i - 10000;
                }

                flag = !flag;
                bucket[i]--;
            }
        }

        return sum;
    }
}