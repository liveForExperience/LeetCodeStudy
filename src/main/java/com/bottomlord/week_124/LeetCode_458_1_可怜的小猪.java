package com.bottomlord.week_124;

/**
 * @author chen yue
 * @date 2021-11-25 08:39:42
 */
public class LeetCode_458_1_可怜的小猪 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int times = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(times));
    }
}
