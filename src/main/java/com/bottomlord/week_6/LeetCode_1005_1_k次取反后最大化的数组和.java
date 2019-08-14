package com.bottomlord.week_6;

public class LeetCode_1005_1_k次取反后最大化的数组和 {
    public int largestSumAfterKNegations(int[] A, int K) {
        int[] bucket = new int[201];
        for (int num: A) {
            bucket[num + 100]++;
        }

        int sum = 0;
        Integer pre = null;
        for (int i = 0; i < bucket.length; i++) {
            if (i < 100 && bucket[i] > 0) {
                pre = i - 100;
                if (K > bucket[i]) {
                    K -= bucket[i];
                    sum += -bucket[i] * (i - 100);
                    continue;
                }

                if (K > 0) {
                    sum += (bucket[i] - 2 * K) * (i - 100);
                    K = 0;
                    continue;
                }

                sum += bucket[i] * (i - 100);
            }

            if (i >= 100 && bucket[i] > 0) {
                if (K > 0) {
                    K %= 2;
                    if (K == 1) {
                        if (pre == null) {
                            sum += -(i - 100) + (bucket[i] - 1) * (i - 100);
                            K = 0;
                        } else {
                            if (Math.abs(pre) < i - 100) {
                                sum += (i - 100) - 2 * Math.abs(pre) + (bucket[i] - 1) * (i - 100);
                            } else {
                                sum += (bucket[i] - 2) * (i - 100);
                            }
                            K = 0;
                        }
                        continue;
                    }
                }
                sum += bucket[i] * (i - 100);
            }
        }

        return sum;
    }
}
