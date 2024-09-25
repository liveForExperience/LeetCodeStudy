package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-26 08:19:37
 */
public class LeetCode_881_1_救生艇 {
    public int numRescueBoats(int[] people, int limit) {
        int[] bucket = new int[limit + 1];
        for (int weight : people) {
            bucket[weight]++;
        }

        int count = 0;

        for (int i = bucket.length - 1; i >= 1; i--) {
            if (bucket[i] == 0) {
                continue;
            }

            while (bucket[i] > 0) {
                int target = Math.min(limit - i, i);
                bucket[i]--;
                for (int j = target; j >= 1; j--) {
                    if (bucket[j] > 0) {
                        bucket[j]--;
                        break;
                    }
                }

                count++;
            }
        }

        return count;
    }
}
