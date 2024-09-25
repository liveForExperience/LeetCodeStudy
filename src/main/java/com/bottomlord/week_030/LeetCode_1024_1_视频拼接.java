package com.bottomlord.week_030;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/31 13:16
 */
public class LeetCode_1024_1_视频拼接 {
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, (x, y) -> y[1] - x[1]);
        int x = T, index = 0, len = clips.length, count = 0;

        while (T != 0) {
            if (index < len && clips[index][1] >= T) {
                x = Math.min(x, clips[index][0]);
                index++;
            } else {
                if (x < T) {
                    T = x;
                    count++;
                } else {
                    return -1;
                }
            }
        }
        return count;
    }
}
