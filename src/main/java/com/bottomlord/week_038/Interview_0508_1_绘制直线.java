package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/29 20:58
 */
public class Interview_0508_1_绘制直线 {
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int[] ans = new int[length];
        int offset = y * w / 32;
        int head = x1 / 32 + offset;
        int rear = x2 / 32 + offset;
        for (int i = head; i <= rear; i++) {
            ans[i] = -1;
        }
        ans[head] &= -1 >>> (x1 % 32);
        ans[rear] &= Integer.MIN_VALUE >> (x2 % 32);
        return ans;
    }
}
