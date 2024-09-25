package com.bottomlord.week_108;

/**
 * @author ChenYue
 * @date 2021/8/3 8:45
 */
public class LeetCode_1426_2 {
    public int countElements(int[] arr) {
        boolean[] bucket = new boolean[1002];
        for (int num : arr) {
            bucket[num] = true;
        }

        int ans = 0;
        for (int num : arr) {
            if (bucket[num + 1]) {
                ans++;
            }
        }
        return ans;
    }
}
