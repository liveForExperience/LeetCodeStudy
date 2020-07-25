package com.bottomlord.week_055;

/**
 * @author ChenYue
 * @date 2020/7/24 8:48
 */
public class LeetCode_158_1_用Read4读取N个字符II extends Read4 {
    private int point;
    private int count;
    private char[] tmp = new char[4];
    public int read(char[] buf, int n) {
        int total = 0;
        while (total < n) {
            if (point == 0) {
                count = read4(tmp);
            }

            if (count == 0) {
                break;
            }

            while (total < n && point < count) {
                buf[total++] = tmp[point++];
            }

            if (point == count) {
                point = 0;
            }

            if (count < 4) {
                break;
            }
        }

        return total;
    }
}
