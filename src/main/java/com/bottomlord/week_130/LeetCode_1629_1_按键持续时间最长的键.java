package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-09 20:55:49
 */
public class LeetCode_1629_1_按键持续时间最长的键 {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int[] bucket = new int[26];
        int max = 0;
        char[] cs = keysPressed.toCharArray();
        for (int i = 0; i < releaseTimes.length; i++) {
            bucket[cs[i] - 'a'] = Math.max(i == 0 ? releaseTimes[i] : releaseTimes[i] - releaseTimes[i - 1], bucket[cs[i] - 'a']);
            max = Math.max(max, bucket[cs[i] - 'a']);
        }

        for (int i = 25; i >= 0; i--) {
            if (bucket[i] == max) {
                return (char) (i + 'a');
            }
        }

        return ' ';
    }
}
