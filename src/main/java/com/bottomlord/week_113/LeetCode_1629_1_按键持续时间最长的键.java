package com.bottomlord.week_113;

/**
 * @author chen yue
 * @date 2021-09-07 08:23:05
 */
public class LeetCode_1629_1_按键持续时间最长的键 {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int max = -1; char ans = ' ';
        for (int i = 0; i < keysPressed.length(); i++) {
            int time = i == 0 ? releaseTimes[i] : releaseTimes[i] - releaseTimes[i - 1];
            if (time > max) {
                max = time;
                ans = keysPressed.charAt(i);
            } else if (time == max && keysPressed.charAt(i) > ans) {
                ans = keysPressed.charAt(i);
            }
        }
        return ans;
    }
}
