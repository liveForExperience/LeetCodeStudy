package com.bottomlord.week_126;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-12-06 08:49:15
 */
public class LeetCode_1913_2 {
    public int maxProductDifference(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();

        int[] bucket = new int[max + 1];
        for (int num : nums) {
            bucket[num]++;
        }

        int count = 0;
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < bucket.length;) {
            if (bucket[i] > 0) {
                bucket[i]--;
                count++;

                if (count == 1) {
                    a = i;
                } else {
                    b = i;
                }
            } else {
                i++;
            }

            if (count == 2) {
                break;
            }
        }

        for (int i = bucket.length - 1; i >= 0;) {
            if (bucket[i] > 0) {
                bucket[i]--;
                count++;

                if (count == 3) {
                    c = i;
                } else if (count == 4) {
                    d = i;
                }
            } else {
                i--;
            }

            if (count == 4) {
                break;
            }
        }

        return c * d - a * b;
    }
}
