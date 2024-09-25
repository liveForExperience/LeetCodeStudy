package com.bottomlord.week_008;

public class LeetCode_7_1_整数反转 {
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }

        String numStr = Integer.toString(x);
        if (x < 0) {
            numStr = numStr.substring(1);
        }

        char[] nums = numStr.toCharArray();
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            nums[head] ^= nums[tail];
            nums[tail] ^= nums[head];
            nums[head] ^= nums[tail];

            head++;
            tail--;
        }

        int index = 0;
        while (nums[index++] == '0');

        StringBuilder sb = new StringBuilder();
        for (; index < nums.length; index++) {
            sb.append(nums[index]);
        }

        if (x < 0) {
            sb.insert(0, "-");
        }

        long ansL = Long.parseLong(sb.toString());
        return ansL > Integer.MAX_VALUE ? 0 :(int)ansL;
    }
}
