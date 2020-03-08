package com.bottomlord.contest_20200308;

/**
 * @author ThinkPad
 * @date 2020/3/8 10:40
 */
public class Contest_2_灯泡开关III {
    public int numTimesAllBlue(int[] light) {
        int count = 0, max = 0, ans = 0;
        for (int index : light) {
            count++;
            max = Math.max(index, max);

            if (max == count) {
                ans++;
            }
        }

        return ans;
    }
}
