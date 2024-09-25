package com.bottomlord.week_045;

/**
 * @author ChenYue
 * @date 2020/5/14 17:17
 */
public class Interview_1719_3 {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2, xor = 0;
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }

        for (int num : nums) {
            xor ^= num;
        }

        int diff = xor & (-xor);

        int[] ans = new int[2];
        for (int i = 1; i <= n; i++) {
            if ((diff & i) == 0) {
                ans[0] ^= i;
            } else {
                ans[1] ^= i;
            }
        }

        for (int num : nums) {
            if ((diff & num) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }
}
