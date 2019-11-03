package com.bottomlord.contest_20191103;

public class Contest_2_统计优美子数组 {
    public int numberOfSubarrays(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = nums[i] == 1 ? 1 : 0;
            if (count == k) {
                ans++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % 2 == 1) {
                    count++;
                }

                if (count == k) {
                    ans++;
                }

                if (count > k) {
                    break;
                }
            }
        }

        return ans;
    }

    public int numberOfSubarrays2(int[] nums, int k) {
        return 0;
    }

    public static void main(String[] args) {
        Contest_2_统计优美子数组 t = new Contest_2_统计优美子数组();
        t.numberOfSubarrays(new int[]{2044,96397,50143}, 1);
    }
}