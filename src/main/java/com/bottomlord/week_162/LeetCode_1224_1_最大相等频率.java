package com.bottomlord.week_162;

/**
 * @author chen yue
 * @date 2022-08-18 07:15:54
 */
public class LeetCode_1224_1_最大相等频率 {
    public int maxEqualFreq(int[] nums) {
        int[] count = new int[100001], countFreq = new int[100001];
        int maxCount = 0, ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (countFreq[count[num]] != 0) {
                countFreq[count[num]]--;
            }

            count[num]++;
            countFreq[count[num]]++;
            maxCount = Math.max(maxCount, count[num]);

            boolean ok = maxCount == 1 ||
                    (countFreq[1] == 1 && maxCount * countFreq[maxCount] == i) ||
                    (countFreq[maxCount] == 1 && countFreq[maxCount - 1] * (maxCount - 1) == i + 1 - maxCount);

            if (ok) {
                ans = i + 1;
            }
        }

        return ans;
    }
}
