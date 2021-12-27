package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2021-12-27 08:53:01
 */
public class LeetCode_825_1_适龄的朋友 {
    public int numFriendRequests(int[] ages) {
        int n = ages.length, count = 0;
        for (int i = 0; i < n; i++) {
            int ageA = ages[i];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                if (ages[j] <= ageA * 0.5 + 7 ||
                ages[j] > ages[i] ||
                (ages[j] > 100 && ages[i] < 100)) {
                    continue;
                }

                count++;
            }
        }

        return count;
    }
}
