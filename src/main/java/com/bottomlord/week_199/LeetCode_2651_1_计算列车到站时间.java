package com.bottomlord.week_199;

/**
 * @author chen yue
 * @date 2023-05-03 13:31:19
 */
public class LeetCode_2651_1_计算列车到站时间 {
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
