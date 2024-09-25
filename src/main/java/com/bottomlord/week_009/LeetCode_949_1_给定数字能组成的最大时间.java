package com.bottomlord.week_009;

public class LeetCode_949_1_给定数字能组成的最大时间 {
    public String largestTimeFromDigits(int[] A) {
        int lte2 = 0, lte3 = 0, lte5 = 0;
        int[] bucket = new int[10];
        for (int num : A) {
            bucket[num]++;
            if (num <= 2) {
                lte2++;
                lte3++;
                lte5++;
            } else if (num <= 3) {
                lte3++;
                lte5++;
            } else if (num <= 5) {
                lte5++;
            }
        }

        if (lte2 < 1 || lte5 < 2) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int one = -1;
        for (int i = 2; i >= 0; i--) {
            if (bucket[i] > 0) {
                if (i == 2 && (lte3 < 2 || lte5 < 3)) {
                    continue;
                }

                bucket[i]--;
                sb.append(i);
                one = i;
                break;
            }
        }

        if (one == -1) {
            return "";
        }

        int two = -1;
        if (one == 2) {
            for (int i = 3; i >= 0; i--) {
                if (bucket[i] > 0) {
                    bucket[i]--;
                    sb.append(i);
                    two = i;
                    break;
                }
            }
        } else {
            for (int i = 9; i >= 0; i--) {
                if (bucket[i] > 0) {
                    bucket[i]--;
                    sb.append(i);
                    two = i;
                    break;
                }
            }
        }

        if (two == -1) {
            return "";
        }

        sb.append(":");

        int three = -1;
        for (int i = 5; i >= 0; i--) {
            if (bucket[i] > 0) {
                bucket[i]--;
                sb.append(i);
                three = i;
                break;
            }
        }

        if (three == -1) {
            return "";
        }

        int four = -1;
        for (int i = 9; i >= 0; i--) {
            if (bucket[i] > 0) {
                bucket[i]--;
                sb.append(i);
                four = i;
                break;
            }
        }

        return four == -1 ? "" : sb.toString();
    }
}
