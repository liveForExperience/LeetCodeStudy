package com.bottomlord.week_119;

/**
 * @author chen yue
 * @date 2021-10-23 14:31:20
 */
public class LeetCode_492_1_构造矩形 {
    public int[] constructRectangle(int area) {
        int head = 1, tail = area;
        int[] ans = new int[2];
        while (head <= tail) {
            int product = head * tail;

            if (product == area) {
                ans[1] = head;
                ans[0] = tail;
                head++;
                tail--;
            } else if (product > area) {
                tail--;
            } else {
                head++;
            }
        }

        return ans;
    }
}
