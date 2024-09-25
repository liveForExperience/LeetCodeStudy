package com.bottomlord.week_055;

/**
 * @author ChenYue
 * @date 2020/7/24 8:29
 */
public class LeetCode_157_1_用Read4读取N个字符 extends Read4 {
    public int read(char[] buf, int n) {
        int i = 0;
        while (n > 0) {
            char[] arr = new char[4];
            int len = read4(arr);
            for (int j = 0; j < Math.min(len, n); j++) {
                buf[i++] = arr[j];
            }
            n -= 4;
        }

        return i;
    }
}
