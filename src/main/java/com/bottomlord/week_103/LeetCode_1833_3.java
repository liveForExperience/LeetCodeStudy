package com.bottomlord.week_103;

/**
 * @author ChenYue
 * @date 2021/7/2 8:33
 */
public class LeetCode_1833_3 {
    public int maxIceCream(int[] costs, int coins) {
        int max = 0;
        for (int cost : costs) {
            max = Math.max(max, cost);
        }
        int[] arr = new int[max + 1];

        for (int cost : costs) {
            arr[cost]++;
        }

        int count = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num == 0) {
                continue;
            }

            if (i * num + sum <= coins) {
                sum += i * num;
                count += num;
            } else {
                count += (coins - sum) / i;
                return count;
            }
        }

        return count;
    }
}
